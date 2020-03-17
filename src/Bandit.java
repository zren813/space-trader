import java.util.Random;

public class Bandit {
    private int credit;
    private Random random = new Random();
    private int creditDemand = random.nextInt(500);
    private String description;
    private int shipDamage;
    private Planet[] planetArray = MapViewController.getPlanetArray();

    public Bandit() {
        this.credit = 1000;
        this.shipDamage = random.nextInt(20);
        this.description = String.format("This is a notorious  Bandit. There are 3 actions you can take.\n\n" +
            "1. Pay me %d. If you dont have enough credit, just turn in all of your items in the ship. " +
            "If you dot't have either enough credit or ship, I will ruin your ship. Don't mess around me!\n\n" +
            "2. Flee like a coward, but you better have a good pilot skill. " +
            "Oh by the way, I hate cowards! I will catch you, then take all your credits and ruin you ship\n\n" +
            "3. Be a man and fight with me. You better have a good fighting skill to take this action" +
            "If you lose, I will kick you ass, take all your money, and ruin your ship.", creditDemand);
    }

    public String getDescription() {
        return description;
    }

    /**
     * This method activates when the player clicks the pay bandit button
     *
     * @param player player
     * @param ship   ship
     * @return return the message describing what was going on
     */
    public String chargeCredit(Player player, Ship ship) {
        String message;
        int playerBlanace = player.getBalance();
        if (playerBlanace >= creditDemand) {
            player.setBalance(playerBlanace - creditDemand);
            message = String.format("You have paid the bandit %d.", creditDemand);
        } else if (!ship.checkInventoryEmpty()) {
            ship.setInventoryEmpty();
            message = "You don't have enough credit. All goods in your inventory are robbed.";
        } else {
            ship.setHealth(ship.getHealth() - shipDamage);
            message = String.format("You don't have enough credit and any goods in your ship. Your ship is damaged by %d.", shipDamage);
        }
        player.setCurrentPlanet(planetArray[MapViewController.getPlanetClickedID()]);
        return message;
    }

    /**
     * this method activates when the player clicks the flee back button
     *
     * @param player player
     * @param ship   ship
     * @return return the corresponding message
     */
    public String dealWithFleeingPlayer(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getPilotSkill() > mask) {
            int fuelCost = PlanetGenerator.getDistanceArray()[MapViewController.getPlanetClickedID()] / 10;
            ship.setFuelCapacity(ship.getCargoCapacity() - fuelCost);
            return String.format("You have successfully flee back, but you lost %d gallons fuel", fuelCost);
        } else {
            player.setBalance(0);
            ship.setHealth(ship.getHealth() - shipDamage);
            player.setCurrentPlanet(planetArray[MapViewController.getPlanetClickedID()]);
            return String.format("You failed to flee back and lost all money. Also, your ship is damaged by %d.", shipDamage);
        }
    }

    /**
     * This method activates when the player clicks the fight button
     *
     * @param player player
     * @param ship   ship
     * @return return the corresponding result message
     */
    public String fightWithPlayer(Player player, Ship ship) {
        int mask = random.nextInt(10);
        String message;
        if (player.getFighterSkill() > mask) {

            int robbedMoney = random.nextInt(credit);
            player.setBalance(player.getBalance() + robbedMoney);
            message = String.format("You won the fight. You robbed %d from the bandit.", robbedMoney);
        } else {
            player.setBalance(0);
            ship.setHealth(ship.getHealth() - shipDamage);
            message = String.format("You lost the fight and all money. Your ship is damaged by %d.", shipDamage);
        }
        player.setCurrentPlanet(planetArray[MapViewController.getPlanetClickedID()]);
        return message;
    }

}
