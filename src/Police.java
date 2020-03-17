import java.util.Random;

public class Police {
    private Random random = new Random();
    private Planet currentPlanet;
    private Planet desiredPlanet;
    private Good wantedGood;
    private int wantedGoodIndex;
    private int wantedGoodNum;

    public Police (Planet currentPlanet, Planet desiredPlanet, Ship ship) {
        this.currentPlanet = currentPlanet;
        this.desiredPlanet = desiredPlanet;
        Good[] shipInventory = ship.getItemInventory();
        for (int i = 0; i < 15; i++) {
            if (shipInventory[i].getQuantity() != 0 ) {
                wantedGood = shipInventory[i];
                wantedGoodIndex = i;
                wantedGoodNum = shipInventory[i].getQuantity();
                break;
            }
        }
    }

    public String encounterPolice() {
        return "I am Officer "  + ". We suspect your " + wantedGoodNum + " " + wantedGood.getName()
                + " are stolen items. We are going to take them back.";
    }

    public void forfeitPolice (Player player, Ship ship) {
        ship.removeItems(wantedGoodIndex, wantedGoodNum);
        player.setCurrentPlanet(desiredPlanet);
    }

    public String fleeBack (Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getPilotSkill() > mask) {
            player.setCurrentPlanet(currentPlanet);
            return "You have successfully flew back.";
        } else {
            player.setCurrentPlanet(currentPlanet);
            ship.removeItems(wantedGoodIndex, wantedGoodNum);
            ship.setHealth(ship.getHealth() - random.nextInt(20));
            int finedMoney;
            if (player.getBalance() < 200) {
                finedMoney = player.getBalance();
                player.setBalance(0);
            } else {
                player.setBalance(player.getBalance() - 200);
                finedMoney = 200;
            }
            return "You failed to flew back. You are charged " + finedMoney
                    + " credits. Your ship is damaged. And your items are confiscated.";
        }
    }

    public String flightPolice (Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getFighterSkill() > mask) {
            player.setCurrentPlanet(desiredPlanet);
            return "You won the fight.";
        } else {
            player.setBalance(0);
            ship.removeItems(wantedGoodIndex, wantedGoodNum);
            ship.setHealth(ship.getHealth() - random.nextInt(50));
            player.setCurrentPlanet(currentPlanet);
            return "You lost the fights. You lose all your credit. Your ship is damaged and your items are confiscated.";

        }
    }

}
