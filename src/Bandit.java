import java.util.Random;

public class Bandit {
    private String name;
    private int credit;
    private Random random = new Random();
    private Planet previousPlanet;
    private Planet desiredPlanet;

    public Bandit (Planet previousPlanet, Planet desiredPlanet) {
        name = NpcNameGenerator.getName();
        credit = random.nextInt(1000);
        this.previousPlanet = previousPlanet;
        this.desiredPlanet = desiredPlanet;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    /**
     * This method activates when the player clicks the pay bandit button
     * @param player player
     * @param ship ship
     */
    public void payBandit(Player player, Ship ship) {
        int banditDemand = random.nextInt(1000);
        int playerBalance =  player.getBalance();
        if (playerBalance > banditDemand) {
            playerBalance = playerBalance - banditDemand;
            player.setBalance(playerBalance);
        } else if (!ship.checkInventoryEmpty()) {
            ship.setInventoryEmpty();
        } else {
            int banditDamage = random.nextInt(20);
            ship.setHealth(ship.getHealth() - banditDamage);
        }
        player.setCurrentPlanet(desiredPlanet);
        // Fuel level needs to change when player tries to travel
    }

    /**
     * this method activates when the player clicks the flee back button
     * @param player player
     * @param ship ship
     */
    public void backBandit(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getPilotSkill() > mask) {
            player.setCurrentPlanet(previousPlanet);
        }
        else {
            player.setBalance(0);
            int banditDamage = random.nextInt(20);
            ship.setHealth(ship.getHealth() - banditDamage);
            player.setCurrentPlanet(previousPlanet);
        }
    }

    /**
     * This method activates when the player clicks the fight button
     * @param player player
     * @param ship ship
     */
    public void fightBandit(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getFighterSkill() > mask) {
            player.setCurrentPlanet(desiredPlanet);
            player.setBalance(player.getBalance() + random.nextInt(credit));
        } else {
            player.setCurrentPlanet(previousPlanet);
            player.setBalance(0);
            int banditDamage = random.nextInt(20);
            ship.setHealth(ship.getHealth() - banditDamage);
        }
    }

}
