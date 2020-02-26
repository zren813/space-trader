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
    private Text good1PriceText;
    @FXML
    private Text good2PriceText;
    @FXML
    private Text good3PriceText;
    @FXML
    public Text good1CapacityText;
    @FXML
    public Text good2CapacityText;
    @FXML
    public Text good3CapacityText;
    @FXML
    private Text good1TechLevelText;
    @FXML
    private Text good2TechLevelText;
    @FXML
    private Text good3TechLevelText;
    @FXML
    private Spinner<Integer> good1Spinner;
    @FXML
    private Spinner<Integer> good2Spinner;
    @FXML
    private Spinner<Integer> good3Spinner;
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
        // add all UI as arrays
        goodNameText = new Text[]{good1NameText, good2NameText, good3NameText};
        goodPriceText = new Text[]{good1PriceText, good2PriceText, good3PriceText};
        goodCapacityText = new Text[]{good1CapacityText, good2CapacityText, good3CapacityText};
        goodTechLevelText = new Text[]{good1TechLevelText, good2TechLevelText, good3TechLevelText};
        goodSpinner = new Spinner[]{good1Spinner, good2Spinner, good3Spinner};

        // initialize goods
        if(!isopened){
            isopened = true;
            goodGenerater = new GoodGenerater();
        }

        // assign needed object
        numberOfGood =goodGenerater.getNumberOfGood();
        good = goodGenerater.getGood();

        player = MapController.getPlayer();
        merchantDiscount = (10.0 - player.getMerchantSkill()) / 10;

        ship = player.getShip();
        shipInventory = ship.getItemInventory();
        if (shipInventory == null) {
            shipInventory = new Good[GoodGenerater.getNumberOfGood()];
            for (int i = 0; i < GoodGenerater.getNumberOfGood(); i++) {
                shipInventory[i] = new Good(good[i].getName(), good[i].getTechLevel(), good[i].getVolume());
            }
        }
        ship.setItemInventory(shipInventory);

        // set up layout of the UI
        updateGoodInfo();
        updateCharacterInfo();
    }

    public void updateGoodInfo(){
        // update goods' price according to merchant discount
        Good.setBasePrice((int) (Good.getBasePrice() * merchantDiscount));
        for (int i = 0; i < numberOfGood; i++) {
            good[i].calculatePrice();
        }
        // update all ui texts
        for (int i = 0; i < numberOfGood; i++) {
            goodNameText[i].setText(good[i].getName());
            goodPriceText[i].setText("$" + good[i].getPrice() + "/$" + (int) (good[i].getPrice() * sellDiscount));
            goodCapacityText[i].setText(String.valueOf(good[i].getVolume()));
            goodTechLevelText[i].setText(String.valueOf(good[i].getTechLevel()));
        }
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


    public void goodBuyBtnPressed(ActionEvent actionEvent) {
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
                if(goodSpinner[i].getValue() !=0){
                    shipInventory[i].setQuantity(shipInventory[i].getQuantity() + goodSpinner[i].getValue());
                }
            }
            ship.setItemInventory(shipInventory);


        } else {
            errorMessage.setText("You don't have enough capacity/balance!");
        }
        //update UI
        resetSpinner();
        updateCharacterInfo();

    }

    public void goodSellBtnPressed(ActionEvent actionEvent) {
        // want to sell quantity
        int wts1 = good1Spinner.getValue();
        int wts2 = good2Spinner.getValue();
        int wts3 = good3Spinner.getValue();
        // check if ship have enough items to sell
        if (wts1 <= shipInventory[0].getQuantity() && wts2 <= shipInventory[1].getQuantity() && wts3 <= shipInventory[2].getQuantity()) {
            // update player's balance and ship's capacity and inventory
            int totalRevenue = (int) (sellDiscount * (wts1 * good[0].getPrice() + wts2 * good[1].getPrice() + wts3 * good[2].getPrice()));
            int totalGainCapacity = wts1 * good[0].getVolume() + wts2 * good[1].getVolume() + wts3 * good[2].getVolume();
            player.setBalance(player.getBalance() + totalRevenue);
            ship.setCargoCapacity(ship.getCargoCapacity() + totalGainCapacity);

            shipInventory[0].setQuantity(shipInventory[0].getQuantity() - wts1);
            shipInventory[1].setQuantity(shipInventory[1].getQuantity() - wts2);
            shipInventory[2].setQuantity(shipInventory[2].getQuantity() - wts3);
            ship.setItemInventory(shipInventory);
        } else {
            errorMessage.setText("You don't have enough items to sell!");
        }
        // update UI
        resetSpinner();
        updateCharacterInfo();
    }
}
