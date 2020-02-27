import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class MarketController {

    @FXML
    private Text good1NameText;
    @FXML
    private Text good2NameText;
    @FXML
    private Text good3NameText;
    @FXML
    private Text good4NameText;
    @FXML
    private Text good5NameText;
    @FXML
    private Text good6NameText;
    @FXML
    private Text good7NameText;
    @FXML
    private Text good8NameText;
    @FXML
    private Text good9NameText;
    @FXML
    private Text good10NameText;
    @FXML
    private Text good11NameText;
    @FXML
    private Text good12NameText;
    @FXML
    private Text good13NameText;
    @FXML
    private Text good14NameText;
    @FXML
    private Text good15NameText;
    @FXML
    private Text good1PriceText;
    @FXML
    private Text good2PriceText;
    @FXML
    private Text good3PriceText;
    @FXML
    private Text good4PriceText;
    @FXML
    private Text good5PriceText;
    @FXML
    private Text good6PriceText;
    @FXML
    private Text good7PriceText;
    @FXML
    private Text good8PriceText;
    @FXML
    private Text good9PriceText;
    @FXML
    private Text good10PriceText;
    @FXML
    private Text good11PriceText;
    @FXML
    private Text good12PriceText;
    @FXML
    private Text good13PriceText;
    @FXML
    private Text good14PriceText;
    @FXML
    private Text good15PriceText;
    @FXML
    public Text good1CapacityText;
    @FXML
    public Text good2CapacityText;
    @FXML
    public Text good3CapacityText;
    @FXML
    public Text good4CapacityText;
    @FXML
    public Text good5CapacityText;
    @FXML
    public Text good6CapacityText;
    @FXML
    public Text good7CapacityText;
    @FXML
    public Text good8CapacityText;
    @FXML
    public Text good9CapacityText;
    @FXML
    public Text good10CapacityText;
    @FXML
    public Text good11CapacityText;
    @FXML
    public Text good12CapacityText;
    @FXML
    public Text good13CapacityText;
    @FXML
    public Text good14CapacityText;
    @FXML
    public Text good15CapacityText;
    @FXML
    private Spinner<Integer> good1Spinner;
    @FXML
    private Spinner<Integer> good2Spinner;
    @FXML
    private Spinner<Integer> good3Spinner;
    @FXML
    private Spinner<Integer> good4Spinner;
    @FXML
    private Spinner<Integer> good5Spinner;
    @FXML
    private Spinner<Integer> good6Spinner;
    @FXML
    private Spinner<Integer> good7Spinner;
    @FXML
    private Spinner<Integer> good8Spinner;
    @FXML
    private Spinner<Integer> good9Spinner;
    @FXML
    private Spinner<Integer> good10Spinner;
    @FXML
    private Spinner<Integer> good11Spinner;
    @FXML
    private Spinner<Integer> good12Spinner;
    @FXML
    private Spinner<Integer> good13Spinner;
    @FXML
    private Spinner<Integer> good14Spinner;
    @FXML
    private Spinner<Integer> good15Spinner;
    @FXML
    private Text playerInfoText;

    @FXML
    private Text errorMessage;
    private Text goodNameText[];
    private Text goodPriceText[];
    private Text goodCapacityText[];
    private Text goodTechLevelText[];
    private Spinner<Integer> goodSpinner[];
    private Text goodInfoText[];

    private Good good[];

    private Player player;
    private Ship ship;
    private Good[] shipInventory;
    private int currentPlanetTechLevel;
    private int[] currentMarketBuyingPrices;
    private int[] currentMarketSellingPrices;
    private double sellDiscount = 0.8;
    private double merchantDiscount;
    private int numberOfGood;
    private static GoodGenerater goodGenerater;
    private static boolean isopened = false;

    public void initialize() {
        Random random = new Random();

        goodNameText = new Text[]{good1NameText, good2NameText, good3NameText, good4NameText, good5NameText,
                good6NameText, good7NameText, good8NameText, good9NameText, good10NameText, good11NameText,
                good12NameText, good13NameText, good14NameText, good15NameText};
        goodPriceText = new Text[]{good1PriceText, good2PriceText, good3PriceText, good4PriceText,
                good5PriceText, good6PriceText, good7PriceText, good8PriceText, good9PriceText, good10PriceText,
                good11PriceText, good12PriceText, good13PriceText, good14PriceText, good15PriceText};
        goodCapacityText = new Text[]{good1CapacityText, good2CapacityText, good3CapacityText, good4CapacityText,
                good5CapacityText, good6CapacityText, good7CapacityText, good8CapacityText, good9CapacityText,
                good10CapacityText, good11CapacityText, good12CapacityText, good13CapacityText, good14CapacityText,
                good15CapacityText};
        goodSpinner = new Spinner[]{good1Spinner, good2Spinner, good3Spinner, good4Spinner, good5Spinner,
                good6Spinner, good7Spinner, good8Spinner, good9Spinner, good10Spinner, good11Spinner,
                good12Spinner, good13Spinner, good14Spinner, good15Spinner};

        // initialize goods and assign good, player and the ship
        if (!isopened){
            isopened = true;
            goodGenerater = new GoodGenerater();
        }
        numberOfGood = goodGenerater.getNumberOfGood();
        good = goodGenerater.getGood();

        player = MapController.getPlayer();
        merchantDiscount = (10.0 - player.getMerchantSkill()) / 10;

        ship = player.getShip();
        shipInventory = ship.getItemInventory();
        if (shipInventory == null) {
            shipInventory = new Good[GoodGenerater.getNumberOfGood()];
            for (int i = 0; i < GoodGenerater.getNumberOfGood(); i++) {
                shipInventory[i] = new Good(good[i].getName(), good[i].getPrice(), good[i].getVolume());
            }
        }
        ship.setItemInventory(shipInventory);

        //calculate buying and selling prices on current market based on planet's tech level and player's data
        //get the tech level of current planet
        Planet[] planetArray = PlanetViewController.getWorldGenerator().getPlanetArray();
        currentPlanetTechLevel = planetArray[PlanetViewController.getIndexPass()].getTechnologyLevel();
        //initialize two arrays that save the current prices
        currentMarketBuyingPrices = new int[numberOfGood];
        currentMarketSellingPrices = new int[numberOfGood];
        //calculate prices
        for (int i = 0; i < numberOfGood; i++) {
            currentMarketBuyingPrices[i] = good[i].getPrice() + (currentPlanetTechLevel * 10) + random.nextInt(100);
            currentMarketSellingPrices[i] =good[i].getPrice() + ((10 - currentPlanetTechLevel) * 10) + random.nextInt(100);
        }

        // set up layout of the UI
        updateGoodInfo();
        updateCharacterInfo();
    }

    public void updateGoodInfo() {
        // update all ui texts
        for (int i = 0; i < numberOfGood; i++) {
            goodNameText[i].setText(good[i].getName());
            goodCapacityText[i].setText(String.valueOf(good[i].getVolume()));
            if (i < (currentPlanetTechLevel / 2 + 1) * 3) {
                goodPriceText[i].setText("$" + currentMarketBuyingPrices[i] + "/$" + (int) (currentMarketSellingPrices[i] * sellDiscount));
            } else {
                goodPriceText[i].setText("-"  + "/$" + (int) (currentMarketSellingPrices[i] * sellDiscount));
            }
        }
    }

    public void updateCharacterInfo() {
        String playerInfo = "";
        playerInfo += String.format("%s(%s)", player.getName(), ship.getName()) +"\n";
        playerInfo += String.format( "Balance: %d", player.getBalance()) +"\n";
        playerInfo += String.format("Ship Capacity: %d", ship.getCargoCapacity()) +"\n";
        playerInfo += String.format("Ship Health: %d", ship.getHealth()) +"\n";
        playerInfo += String.format( "Discount: %.1f", merchantDiscount) +"\n\n";

        playerInfoText.setText(playerInfo);
    }

    public void resetSpinner() {
        for (int i = 0; i < numberOfGood; i++) {
            goodSpinner[i].getValueFactory().setValue(0);
        }
    }

    public void characterUpgradeBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("CharacterUpgrade.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();

    }

    public void exitBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void shipInventoryBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("ShipInventory.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }


    public void goodBuyBtnPressed(ActionEvent actionEvent) {
        int[] numOfItemToBuy = new int[numberOfGood];
        int totalCapacity = 0;
        int totalCost = 0;
        for (int i = 0; i < numberOfGood; i++) {
            numOfItemToBuy[i] = goodSpinner[i].getValue();
            totalCapacity += goodSpinner[i].getValue();
            totalCost += goodSpinner[i].getValue() * currentMarketBuyingPrices[i];
        }
        //check if the capacity and balance are enough
        if (totalCapacity <= ship.getCargoCapacity() && totalCost <= player.getBalance() && checkTechLevel(numOfItemToBuy)) {
            // if they are enough, update balance, capacity and add items to the ship
            player.setBalance(player.getBalance() - totalCost);
            ship.setCargoCapacity(ship.getCargoCapacity() - totalCapacity);
            for (int i = 0; i < numberOfGood; i++) {
                if (goodSpinner[i].getValue() != 0) {
                    shipInventory[i].setQuantity(shipInventory[i].getQuantity() + goodSpinner[i].getValue());
                }
            }
            ship.setItemInventory(shipInventory);
        } else {
            errorMessage.setText("You don't have enough capacity/balance or you try to buy illegal items!");
        }
        //update UI
        resetSpinner();
        updateCharacterInfo();
    }

    public void goodSellBtnPressed(ActionEvent actionEvent) {
        // want to sell quantity
        int[] numOfItemToSell = new int[numberOfGood];
        for (int i = 0; i < numberOfGood; i++) {
            numOfItemToSell[i] = goodSpinner[i].getValue();
        }
        // check if ship have enough items to sell
        if (checkInventory(numOfItemToSell)) {
            // update player's balance and ship's capacity and inventory
            player.setBalance(player.getBalance() + calculateRevenue(numOfItemToSell));
            ship.setCargoCapacity(ship.getCargoCapacity() + calculateCapacityGain(numOfItemToSell));
            for (int i = 0; i < numberOfGood; i++) {
                shipInventory[i].setQuantity(shipInventory[i].getQuantity() - numOfItemToSell[i]);
            }
            ship.setItemInventory(shipInventory);
        } else {
            errorMessage.setText("You don't have enough items to sell!");
        }
        // update UI
        resetSpinner();
        updateCharacterInfo();
    }

    private boolean checkTechLevel(int[] numOfItemToBuy) {
        for (int i = 0; i < numberOfGood; i++) {
            if (numOfItemToBuy[i] != 0 && i >= (currentPlanetTechLevel / 2 + 1) * 3) {
                return false;
            }
        }
        return true;
    }

    private boolean checkInventory(int[] numOfItemToSell) {
        for (int i = 0; i < numberOfGood; i++) {
            if (numOfItemToSell[i] > goodSpinner[i].getValue()) {
                return false;
            }
        }
        return true;
    }

    private int calculateRevenue(int[] numOfItemToSell) {
        int result = 0;
        for (int i = 0; i < numberOfGood; i++) {
            result += numOfItemToSell[i] * good[i].getPrice();
        }
        result *= sellDiscount;
        return result;
    }

    private int calculateCapacityGain(int[] numOfItemToSell) {
        int result = 0;
        for (int i = 0; i < numberOfGood; i++) {
            result += numOfItemToSell[i] * good[i].getVolume();
        }
        return result;
    }
}
