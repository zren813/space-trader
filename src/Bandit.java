import java.util.Random;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Bandit {
    private String name;
    private int credit;
    private Random random = new Random();
    private Planet previousPlanet;
    private Planet desiredPlanet;
    private int banditDemand = random.nextInt(1000);

    public Bandit (Planet previousPlanet, Planet desiredPlanet) {
        this.previousPlanet = previousPlanet;
        this.desiredPlanet = desiredPlanet;
        name = NpcNameGenerator.getName();
        credit = random.nextInt(1000);
    }
    public String encounterBandit() {
        return "My name is " + name + ". Give me " + banditDemand + " credits and I will let you go.";
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
     * @return return the message describing what was going on
     */
    public String payBandit(Player player, Ship ship) {
        int playerBalance =  player.getBalance();
        if (playerBalance > banditDemand) {
            playerBalance = playerBalance - banditDemand;
            player.setBalance(playerBalance);
            MapController.setCurrPlanet(desiredPlanet.getPlanetID());
            player.setCurrentPlanet(desiredPlanet);
            return "You have paid the bandit the desired amount of credit.";
        } else if (!ship.checkInventoryEmpty()) {
            ship.setInventoryEmpty();
            MapController.setCurrPlanet(desiredPlanet.getPlanetID());
            player.setCurrentPlanet(desiredPlanet);
            return "You don't have enough credit. All goods in your inventory are robbed.";
        } else {
            int banditDamage = random.nextInt(20);
            ship.setHealth(ship.getHealth() - banditDamage);
            MapController.setCurrPlanet(desiredPlanet.getPlanetID());
            player.setCurrentPlanet(desiredPlanet);
            return "You don't have enough credit, and you don't have any goods. Your ship is damaged.";
        }
        // Fuel level needs to change when player tries to travel
    }

    /**
     * this method activates when the player clicks the flee back button
     * @param player player
     * @param ship ship
     * @return return the corresponding message
     */
    public String backBandit(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getPilotSkill() > mask) {
            player.setCurrentPlanet(previousPlanet);
            return "You have successfully flee back.";
        }
        else {
            player.setBalance(0);
            int banditDamage = random.nextInt(20);
            ship.setHealth(ship.getHealth() - banditDamage);
            player.setCurrentPlanet(previousPlanet);
            return "You failed to flee back without any damage. Your ship is damaged.";
        }
    }

    /**
     * This method activates when the player clicks the fight button
     * @param player player
     * @param ship ship
     * @return return the corresponding result message
     */
    public String fightBandit(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getFighterSkill() > mask) {
            player.setCurrentPlanet(desiredPlanet);
            player.setBalance(player.getBalance() + random.nextInt(credit));
            return "You won the fight. You robbed some credits from the bandit.";
        } else {
            player.setCurrentPlanet(previousPlanet);
            player.setBalance(0);
            int banditDamage = random.nextInt(20);
            ship.setHealth(ship.getHealth() - banditDamage);
            return "You lost the fight. Your ship is damaged.";
        }
    }

}
