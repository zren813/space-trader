import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Text good1TechLevelText;
    @FXML
    private Text good2TechLevelText;
    @FXML
    private Text good3TechLevelText;
    @FXML
    private Text good4TechLevelText;
    @FXML
    private Text good5TechLevelText;
    @FXML
    private Text good6TechLevelText;
    @FXML
    private Text good7TechLevelText;
    @FXML
    private Text good8TechLevelText;
    @FXML
    private Text good9TechLevelText;
    @FXML
    private Text good10TechLevelText;
    @FXML
    private Text good11TechLevelText;
    @FXML
    private Text good12TechLevelText;
    @FXML
    private Text good13TechLevelText;
    @FXML
    private Text good14TechLevelText;
    @FXML
    private Text good15TechLevelText;
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
    private double sellDiscount = 0.8;
    private double merchantDiscount;
    private int numberOfGood;
    private static GoodGenerater goodGenerater;
    private static boolean isopened = false;

    public void initialize() {
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
        goodTechLevelText = new Text[]{good1TechLevelText, good2TechLevelText, good3TechLevelText, good4TechLevelText,
                good5TechLevelText, good6TechLevelText, good7TechLevelText, good8TechLevelText, good9TechLevelText,
                good10TechLevelText, good11TechLevelText, good12TechLevelText, good13TechLevelText,
                good14TechLevelText, good15TechLevelText};
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
        // update goods' price due to merchant discount
        Good.setBasePrice((int) (Good.getBasePrice() * merchantDiscount));
        for (int i = 0; i < numberOfGood; i++) {
            good[i].calculatePrice();
        }

        ship = player.getShip();
        shipInventory = ship.getItemInventory();
        if (shipInventory == null) {
            shipInventory = new Good[GoodGenerater.getNumberOfGood()];
            for (int i = 0; i < GoodGenerater.getNumberOfGood(); i++) {
                shipInventory[i] = new Good(good[0].getName(), good[0].getTechLevel(), good[0].getVolume());
            }
        }
        ship.setItemInventory(shipInventory);

        Planet[] planetArray = PlanetViewController.getWorldGenerator().getPlanetArray();
        int techLevel = planetArray[PlanetViewController.getIndexPass()]

        // set up layout of the UI
        for (int i = 0; i < numberOfGood; i++) {
            goodNameText[i].setText(good[i].getName());
            if ()
            goodPriceText[i].setText("$" + good[i].getPrice() + "/$" + (int) (good[i].getPrice() * sellDiscount));
            goodCapacityText[i].setText(String.valueOf(good[i].getVolume()));
            goodTechLevelText[i].setText(String.valueOf(good[i].getTechLevel()));
        }
        updateCharacterInfo();
    }

    public void updateCharacterInfo() {
        String playerInfo = "";
        playerInfo += String.format("%s(%s)", player.getName(), ship.getName()) +"\n";
        playerInfo += String.format( "Balance: %d", player.getBalance()) +"\n";
        playerInfo += String.format("Ship Capacity: %d", ship.getCargoCapacity()) +"\n";
        playerInfo += String.format("Ship Health: %d", ship.getHealth()) +"\n";
        playerInfo += String.format( "Discount: %.1f", merchantDiscount) +"\n\n";

        playerInfo += String.format("Inventory:") +"\n";
        playerInfo += String.format("%s: %d", good[0].getName(), ship.getItemInventory()[0].getQuantity()) +"\n";
        playerInfo += String.format("%s: %d", good[1].getName(), ship.getItemInventory()[1].getQuantity()) +"\n";
        playerInfo += String.format("%s: %d", good[2].getName(), ship.getItemInventory()[2].getQuantity()) +"\n";
        playerInfoText.setText(playerInfo);
    }

    public void resetSpinner() {
        good1Spinner.getValueFactory().setValue(0);
        good2Spinner.getValueFactory().setValue(0);
        good3Spinner.getValueFactory().setValue(0);
    }

    public void characterUpgradeBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("CharacterUpgrade.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();

    }

    public void exitBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }


    public void good1BuyBtnPressed(ActionEvent actionEvent) {
        int totalCapacity = 0;
        int totalCost = 0;
        totalCapacity = good1Spinner.getValue() + good2Spinner.getValue() + good3Spinner.getValue();
        totalCost = good1Spinner.getValue() * good[0].getPrice() + good2Spinner.getValue() * good[1].getPrice() + good3Spinner.getValue() * good[2].getPrice();
        //check if the capacity and balance are enough
        if (totalCapacity <= ship.getCargoCapacity() && totalCost <= player.getBalance()) {
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
            errorMessage.setText("You don't have enough capacity/balance!");
        }
        //reset spinner
        resetSpinner();
        // update UI of character's info
        updateCharacterInfo();
    }

    public void good1SellBtnPressed(ActionEvent actionEvent) {
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
        //reset spinner
        resetSpinner();
        // update UI of character's info
        updateCharacterInfo();
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
