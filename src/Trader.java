
import javafx.fxml.FXML;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Trader {
    private Random random = new Random();
    private Good[] traderGood;
    private String description;
    private Player player;
    private Ship ship;
    private Planet[] planetArray;
    private int shipDamageRange = 20;

    public Trader() {
        this.player = MapViewController.getPlayer();
        ship = MapViewController.getShip();
        this.planetArray = MapViewController.getPlanetArray();
        double chanceToGetNegotiated = player.getMerchantSkill() / 10.0;
        double chanceToGetBeat = player.getFighterSkill() / 10.0;
        setUpTraderGood();
        this.description = String.format("This is a trader. There are 3 actions you can take.\n\n" +
                "1. Buy the item you choose. (Status: Balance = %d, Cargo capacity = %d) \n" +
                "2. Ignore the trader and carry on your journey.\n" +
                "3. Rob trader. The trader has some good stuffs. You have %.1f probability to win the fight. " +
                "If you lose, the trader will get angry and ruin your ship.\n" +
                "4. Negotiate price. You have one chance to negotiate the price. " +
                "You have %.1f probability to get 50 percent off for all goods. " +
                "However, the price will double if you fail to negotiate.\n",
            player.getBalance(), ship.getCargoCapacity(), chanceToGetBeat, chanceToGetNegotiated
        );
    }

    public String getDescription() {
        return description;
    }

    private void setUpTraderGood() {
        int numberOfGood = 3;
        Good[] allGood = GoodGenerater.getGood().clone();
        traderGood = new Good[numberOfGood];
        //choose three good randomly from all goods from the generator
        for (int i = 0; i < numberOfGood; i++) {
            Set<Integer> set = new HashSet<>();
            int goodPicked = random.nextInt(allGood.length);
            while (set.contains(goodPicked)) {
                goodPicked = random.nextInt(allGood.length);
            }
            set.add(goodPicked);
            traderGood[i] = allGood[goodPicked];
        }
    }


    public String sellGoodsToPlayer(Player player, Ship ship, int[] numberOfGoodToBuy) {
        int totalCost = 0;
        int totalVolume = 0;
        for (int i = 0; i < numberOfGoodToBuy.length; i++) {
            totalCost += traderGood[i].getBasePrice() * numberOfGoodToBuy[i];
            totalVolume += traderGood[i].getVolume();
        }
        if (totalCost > player.getBalance()) {
            return "You don't have enough money. You missed the chance to buy";
        } else if (totalVolume > ship.getCargoCapacity()) {
            return "You don't have enough cargo capacity. You missed the chance to buy";
        } else {
            player.setBalance(player.getBalance() - totalCost);
            ship.setCargoCapacity(ship.getCargoCapacity() - totalVolume);
            ship.addRandomGood(traderGood, numberOfGoodToBuy);
            return "You have successfully bought the item.";
        }
    }

    public String getRobbed(Player player, Ship ship) {
        int robNumberRange = 3;
        int mask = random.nextInt(10);
        int totalCost = 0;
        int totalVolume = 0;
        int numberOfGoodToRob[] = new int[traderGood.length];

        if (player.getFighterSkill() > mask) {
            for (int i = 0; i < traderGood.length; i++) {
                numberOfGoodToRob[i] = random.nextInt(robNumberRange);
                totalCost += traderGood[i].getBasePrice() * random.nextInt(robNumberRange);
                totalVolume += traderGood[i].getVolume();
            }

            if (ship.getCargoCapacity() < totalVolume) {
                return "You have robbed successfully, but your inventory is full.";
            } else {
                player.setBalance(player.getBalance() - totalCost);
                ship.setCargoCapacity(ship.getCargoCapacity() - totalVolume);
                ship.addRandomGood(traderGood, numberOfGoodToRob);
                return String.format("Success! You robbed %d of %s, %d of %s, and %d of %s",
                    numberOfGoodToRob[0], traderGood[0].getName(),
                    numberOfGoodToRob[1], traderGood[1].getName(),
                    numberOfGoodToRob[2], traderGood[2].getName());
            }
        } else {
            int shipDamage = random.nextInt(shipDamageRange);
            ship.setHealth(ship.getHealth() - shipDamage);
            return String.format("You failed to rob. Your ship is damaged by %d.", shipDamage);
        }
    }

    public String getNegotiated(Player player) {
        int mask = random.nextInt(10);
        if (player.getMerchantSkill() > mask) {
            for (int i = 0; i < traderGood.length; i++) {
                traderGood[i].setBasePrice((int) (traderGood[i].getBasePrice() * 0.5));
            }
            return "You have successfully negotiated the price. The price is half now.";
        } else {
            for (int i = 0; i < traderGood.length; i++) {
                traderGood[i].setBasePrice((int) (traderGood[i].getBasePrice() * 2.0));
            }
            return "The trader is upset. The price is doubled.";
        }
    }


    public Good[] getTraderGood() {
        return traderGood;
    }
}
