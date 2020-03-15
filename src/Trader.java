import java.util.Random;

public class Trader {
    private String name;
    private Random random = new Random();
    private Planet previousPlanet;
    private Planet desiredPlanet;
    private Good[] traderGood;
    private int[] traderGoodPrice;
    private int[] traderGoodNumber;

    public Trader(Planet previousPlanet, Planet desiredPlanet) {
        this.previousPlanet = previousPlanet;
        this.desiredPlanet = desiredPlanet;
        name = NpcNameGenerator.getName();
        Good[] goodFromGenerator = GoodGenerater.getGood();
        traderGood = new Good[3];
        traderGoodPrice = new int[3];
        traderGoodNumber = new int[3];
        //choose three good randomly from all goods from the generator
        for (int i = 0; i < 3; i++) {
            int mask = random.nextInt(15);
            traderGood[i] = goodFromGenerator[mask];
            traderGoodPrice[i] = traderGood[i].getBasePrice() * (random.nextInt(40) + 80) / 100;
            traderGoodNumber[i] = mask;
        }
    }
    public String encounterTrader() {
        return "My name is " + name + ". I have some goods you might you want to take a look.";
    }
    public String buyFromTrader(Player player, Ship ship, int numberOfItem) {//the number is 0, 1 or 2
        if (traderGoodPrice[numberOfItem] <= player.getBalance() &&
                ship.getCargoCapacity() >= traderGood[numberOfItem].getVolume()) {
            player.setBalance(player.getBalance() - traderGoodPrice[numberOfItem]);
            ship.setCargoCapacity(ship.getCargoCapacity() - traderGood[numberOfItem].getVolume());
            ship.addItems(traderGoodNumber[numberOfItem], 1);
            return "You have successfully bought the item.";
        } else {
            return "You failed to buy this item because you don't have enough space or cargo capacity.";
        }
    }

    public void ignoreTrader(Player player, Ship ship) {
        player.setCurrentPlanet(desiredPlanet);
    }

    public String robTrader(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getFighterSkill() > mask) {
            for (int i = 0; i < 3; i++) {
                if (ship.getCargoCapacity() >= traderGood[i].getVolume()) {
                    ship.setCargoCapacity(ship.getCargoCapacity() - traderGood[i].getVolume());
                    ship.addItems(traderGoodNumber[i], 1);
                    player.setCurrentPlanet(desiredPlanet);
                    return "You have robbed successfully. One good is added to your inventory.";
                }
            }
            return "You have robbed successfully, but your inventory is full.";
        } else {
            ship.setHealth(ship.getHealth() - random.nextInt(20));
            player.setCurrentPlanet(desiredPlanet);
            return "You failed to rob. Your ship is damaged.";
        }
    }

    public String negotiateTrader(Player player, Ship ship) {
        int mask = random.nextInt(10);
        if (player.getMerchantSkill() > mask) {
            for (int i = 0; i < 3; i++) {
                traderGoodPrice[i] = (int) (traderGoodPrice[i] * 0.85);
            }
            return "You have successfully negotiated the price.";
        } else {
            for (int i = 0; i < 3; i++) {
                traderGoodPrice[i] = (int) (traderGoodPrice[i] * 1.20);
            }
            return "The trader is upset. The price is rising.";
        }
    }

}
