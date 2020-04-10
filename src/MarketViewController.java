import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketViewController {

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
    private Text good1CapacityText;
    @FXML
    private Text good2CapacityText;
    @FXML
    private Text good3CapacityText;
    @FXML
    private Text good4CapacityText;
    @FXML
    private Text good5CapacityText;
    @FXML
    private Text good6CapacityText;
    @FXML
    private Text good7CapacityText;
    @FXML
    private Text good8CapacityText;
    @FXML
    private Text good9CapacityText;
    @FXML
    private Text good10CapacityText;
    @FXML
    private Text good11CapacityText;
    @FXML
    private Text good12CapacityText;
    @FXML
    private Text good13CapacityText;
    @FXML
    private Text good14CapacityText;
    @FXML
    private Text good15CapacityText;
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

    private Text[] goodNameText;
    private Text[] goodPriceText;
    private Text[] goodCapacityText;
    private Spinner<Integer>[] goodSpinner;


    private static Player player;
    private static Ship ship;
    private Good[] good;
    private static Good[] shipInventory;
    private int currentPlanetTechLevel;
    private int[] currentMarketBuyingPrices;
    private int[] currentMarketSellingPrices;
    private int[] numOfItemToBuy;
    private int[] numOfItemToSell;
    // reselling goods only gain 80% of the original price
    private double sellDiscount = 0.8;
    private double merchantDiscount;
    private int numberOfGood;
    private int currentPlanet;

    public void initialize() {
        setUpUIObjectArray();
        setUpNeededObjects();

        //calculate buying and selling prices
        calculateGoodPrice();

        // set up layout of the UI
        updateGoodInfo();
        updateCharacterInfo();
    }

    public void setUpNeededObjects() {
        // initialize goods and assign good, player and the ship
        player = MapViewController.getPlayer();
        ship = player.getShip();
        good = MapViewController.getGood();
        shipInventory = ship.getItemInventory();

        numberOfGood = good.length;
        numOfItemToBuy = new int[numberOfGood];
        numOfItemToSell = new int[numberOfGood];
        currentMarketBuyingPrices = new int[numberOfGood];
        currentMarketSellingPrices = new int[numberOfGood];
    }

    public void calculateGoodPrice() {
        //on current market based on planet's tech level and player's data
        //get the tech level of current planet
        currentPlanetTechLevel = player.getCurrentPlanet().getTechnologyLevel();
        // the discount depending on merchant skill
        merchantDiscount = (10 - player.getMerchantSkill()) / 10.0;
        //calculate prices
        // high tech goods are cheaper in high level planets
        // low tech goods are cheaper in low level planets
        for (int i = 0; i < numberOfGood; i++) {
            currentMarketBuyingPrices[i] = (int) (good[i].getBasePrice()
                * ((Math.abs(good[i].getTechLevel() - currentPlanetTechLevel)) * 0.1 + 1)
                * merchantDiscount);
            currentMarketSellingPrices[i] = (int) (good[i].getBasePrice()
                * ((Math.abs(good[i].getTechLevel() - currentPlanetTechLevel)) * 0.1 + 1)
                * merchantDiscount);
        }
    }

    public void updateGoodInfo() {
        // update all ui texts
        currentPlanet = player.getCurrentPlanet().getPlanetID();
        for (int i = 0; i < numberOfGood - 1; i++) {
            goodNameText[i].setText(good[i].getName());
            goodCapacityText[i].setText(good[i].getVolume() + "");
            // only showing the good whose level match the tech level of the current planet
            if (good[i].getTechLevel() <= currentPlanetTechLevel) {
                goodPriceText[i].setText("$" + currentMarketBuyingPrices[i]
                    + "/$" + (int) (currentMarketSellingPrices[i] * sellDiscount));
            } else {
                goodPriceText[i].setText("-" + "/$"
                    + (int) (currentMarketSellingPrices[i] * sellDiscount));
            }
        }
        int i = numberOfGood - 1;
        goodNameText[i].setText(good[i].getName());
        goodCapacityText[i].setText(good[i].getVolume() + "");
        if (currentPlanet == 0) {
            goodPriceText[i].setText("$" + currentMarketBuyingPrices[i]
                    + "/" + "-");
        } else {
            goodPriceText[i].setText("-" + "/"
                    + "-");
        }
    }

    public void updateCharacterInfo() {
        String characterInfo = String.format("%s(%s)\n", player.getName(), ship.getName())
            + String.format("Balance: %d\n", player.getBalance())
            + String.format("Ship Capacity: %d\n", ship.getCargoCapacity())
            + String.format("Ship Health: %d\n", ship.getHealth())
            + String.format("Discount: %.1f\n\n", merchantDiscount)
            + String.format("Current Planet Tech Level: %s\n",
                player.getCurrentPlanet().getTechnologyLevel());
        playerInfoText.setText(characterInfo);
    }

    public void resetSpinner() {
        for (int i = 0; i < numberOfGood; i++) {
            goodSpinner[i].getValueFactory().setValue(0);
        }
    }

    public void characterUpgradeBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("CharacterUpgradeView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();

    }

    public void exitBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("MapView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void shipInventoryBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("ShipInventoryView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }


    public void goodBuyBtnPressed(ActionEvent actionEvent) throws IOException {
        numOfItemToBuy = new int[numberOfGood];
        int totalCapacity = 0;
        int totalCost = 0;
        for (int i = 0; i < numberOfGood; i++) {
            numOfItemToBuy[i] = goodSpinner[i].getValue();
            totalCapacity += goodSpinner[i].getValue() * good[i].getVolume();
            totalCost += goodSpinner[i].getValue() * currentMarketBuyingPrices[i];
        }
        //check if the capacity and balance are enough
        if (totalCapacity <= ship.getCargoCapacity() && totalCost <= player.getBalance()
            && checkTechLevel(numOfItemToBuy)) {
            // if they are enough, update balance, capacity and add items to the ship
            player.setBalance(player.getBalance() - totalCost);
            ship.setCargoCapacity(ship.getCargoCapacity() - totalCapacity);
            for (int i = 0; i < numberOfGood; i++) {
                shipInventory[i].setQuantity(shipInventory[i].getQuantity() + numOfItemToBuy[i]);
                numOfItemToBuy[i] = 0;
            }
            if (shipInventory[numberOfGood - 1].getQuantity() > 0) {
                winingDialog();

                Parent configParent = FXMLLoader.load(getClass().getResource("result.fxml"));
                Scene configScene = new Scene(configParent);
                configScene.getStylesheets().add("app.css");

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                window.setScene(configScene);
                window.show();
            }
            ship.setItemInventory(shipInventory);
            errorMessage.setText("");
        } else {
            errorMessage.setText("You don't have "
                + "enough capacity/balance or you try to buy illegal items!");
        }
        //update UI
        resetSpinner();
        updateCharacterInfo();
    }

    public void winingDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("YOU WIN!!!");
        alert.setHeaderText("YOU JUST WIN THE GAME!!");
        alert.setContentText("Congratulations!");
        ButtonType buttonTypeOne = new ButtonType("Continue");
        alert.showAndWait();
    }

    public void goodSellBtnPressed(ActionEvent actionEvent) {
        // want to sell quantity
        numOfItemToSell = new int[numberOfGood];
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
                numOfItemToSell[i] = 0;
            }
            ship.setItemInventory(shipInventory);
            errorMessage.setText("");
        } else {
            errorMessage.setText("You don't have enough items to sell!");
        }
        // update UI
        resetSpinner();
        updateCharacterInfo();
    }

    private void setUpUIObjectArray() {
        goodNameText = new Text[]{good1NameText, good2NameText, good3NameText,
            good4NameText, good5NameText,
            good6NameText, good7NameText, good8NameText, good9NameText,
            good10NameText, good11NameText,
            good12NameText, good13NameText, good14NameText, good15NameText};
        goodPriceText = new Text[]{good1PriceText, good2PriceText, good3PriceText, good4PriceText,
            good5PriceText, good6PriceText, good7PriceText, good8PriceText,
            good9PriceText, good10PriceText,
            good11PriceText, good12PriceText, good13PriceText,
            good14PriceText, good15PriceText};
        goodCapacityText = new Text[]{good1CapacityText, good2CapacityText,
            good3CapacityText, good4CapacityText,
            good5CapacityText, good6CapacityText, good7CapacityText,
            good8CapacityText, good9CapacityText,
            good10CapacityText, good11CapacityText, good12CapacityText,
            good13CapacityText, good14CapacityText,
            good15CapacityText};
        goodSpinner = new Spinner[]{good1Spinner, good2Spinner, good3Spinner,
            good4Spinner, good5Spinner,
            good6Spinner, good7Spinner, good8Spinner, good9Spinner,
            good10Spinner, good11Spinner,
            good12Spinner, good13Spinner, good14Spinner, good15Spinner};
    }

    private boolean checkTechLevel(int[] numOfItemToBuy) {
        for (int i = 0; i < numberOfGood; i++) {
            if (numOfItemToBuy[i] != 0 && good[i].getTechLevel() > currentPlanetTechLevel) {
                return false;
            }
        }
        return true;
    }

    private boolean checkInventory(int[] numOfItemToSell) {
        for (int i = 0; i < numberOfGood; i++) {
            if (numOfItemToSell[i] > shipInventory[i].getQuantity()) {
                return false;
            }
        }
        return true;
    }

    private int calculateRevenue(int[] numOfItemToSell) {
        int result = 0;
        for (int i = 0; i < numberOfGood; i++) {
            result += numOfItemToSell[i] * currentMarketSellingPrices[i];
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
