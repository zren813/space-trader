import java.util.Random;

public class Police {
    private Random random = new Random();
    private Player player;
    private Ship ship;
    private Planet[] planetArray;
    private Good suspiciousGood;
    private int numberOfSuspiciousGood;
    private int shipDamageRange = 20;
    private String description;

    public Police() {
        this.player = MapViewController.getPlayer();
        this.ship = MapViewController.getShip();
        this.planetArray = MapViewController.getPlanetArray();
        setUpSuspiciousGood();
        this.description = String.format("You Encounter a police. He suspect that you stole %d of %s from NPCs. " +
                "You can take 3 actions\n\n" +
                "1. Forfeit the items. You will turn in those spoils to the office and leave right away.\n\n" +
                "2. Flee Back. Try this way if you have a good pilot skill to escape. If you fail, " +
                "the officer will give you a hard time confiscating those spoils, ruining your ship and giving you a ticket.\n\n" +
                "3. Fight Off the police. You better have a good fighting skill to beat the well-trained police, or " +
                "you ship will be caused a huge damage.\n\n"
            , numberOfSuspiciousGood, suspiciousGood.getName());
    }

    private void setUpSuspiciousGood() {
        Good[] allGood = ship.getItemInventory();
        boolean done = false;
        while (!done) {
            int suspiciousGoodID = random.nextInt(allGood.length);
            if (allGood[suspiciousGoodID].getQuantity() != 0) {
                suspiciousGood = allGood[suspiciousGoodID];
                done = true;
            }
        }
        numberOfSuspiciousGood = suspiciousGood.getQuantity() == 1 ? 1 : random.nextInt(suspiciousGood.getQuantity());
    }

    public String getDescription() {
        return description;
    }

    public String acceptStolenGood() {
        ship.subtractSpecificGood(new Good[]{suspiciousGood}, new int[]{numberOfSuspiciousGood});
        player.setCurrentPlanet(planetArray[MapViewController.getPlanetClickedID()]);
        return "You turned in all stolen items. Please don't do that next time!";
    }

    public String dealWithFleeingPlayer() {
        int mask = random.nextInt(10);
        String message;

        if (player.getPilotSkill() > mask) {
            message = "You have successfully flew back.";
        } else {
            ship.subtractSpecificGood(new Good[]{suspiciousGood}, new int[]{numberOfSuspiciousGood});
            int shipDamage = random.nextInt(shipDamageRange);
            ship.setHealth(ship.getHealth() - shipDamage);
            int ticketAmount = random.nextInt(player.getBalance());
            player.setBalance(player.getBalance() - ticketAmount);
            message = String.format("You failed to flew back. You are charged %d credits. " +
                    "Your ship is damaged by %d. And your items are confiscated.",
                ticketAmount, shipDamage);
            player.setCurrentPlanet(planetArray[MapViewController.getPlanetClickedID()]);
        }
        return message;
    }

    public String dealWithFightingOff() {
        int mask = random.nextInt(10);
        String message;
        if (player.getFighterSkill() > mask) {
            message = "The police is on the ground. Congrats! You won the fight.";
        } else {
            ship.subtractSpecificGood(new Good[]{suspiciousGood}, new int[]{numberOfSuspiciousGood});
            int shipDamage = random.nextInt(shipDamageRange);
            ship.setHealth(ship.getHealth() - shipDamage);
            message = String.format("You lost the fights. Your ship gets a serious damaged (%d) " +
                "and your items are confiscated.", shipDamage);
        }
        player.setCurrentPlanet(planetArray[MapViewController.getPlanetClickedID()]);
        return message;
    }

}
