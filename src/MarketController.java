import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Map;

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
    private Text playerAndShipNameText;
    @FXML
    private Text balanceText;
    @FXML
    private Text capacityText;
    @FXML
    private Text good1InfoText;
    @FXML
    private Text good2InfoText;
    @FXML
    private Text good3InfoText;
    @FXML
    private Text errorMessage;

    private Good good[];
    private Player player;
    private Ship ship;
    private Good[] shipInventory;
    private double discount = 0.8;

    public void initialize() {
        // initialize goods and assign good, player and the ship
        GoodGenerater goodGenerater = new GoodGenerater();
        good = goodGenerater.getGood();

        player = MapController.getPlayer();
        ship = player.getShip();
        shipInventory = ship.getItemInventory();
        if(shipInventory==null){
            shipInventory = new Good[GoodGenerater.getNumberOfGood()];
            for (int i = 0; i < GoodGenerater.getNumberOfGood(); i++) {
                shipInventory[i] = new Good(good[0].getName(), good[0].getTechLevel(), good[0].getVolume());
            }
        }
        ship.setItemInventory(shipInventory);

        // set up layout of the UI
        good1NameText.setText(good[0].getName());
        good2NameText.setText(good[1].getName());
        good3NameText.setText(good[2].getName());
        good1PriceText.setText("$" + good[0].getPrice() + "/$" + (int)(good[0].getPrice() * discount));
        good2PriceText.setText("$" + good[1].getPrice() + "/$" + (int)(good[1].getPrice() * discount));
        good3PriceText.setText("$" + good[2].getPrice() + "/$" + (int)(good[2].getPrice() * discount));
        good1CapacityText.setText(String.valueOf(good[0].getVolume()));
        good2CapacityText.setText(String.valueOf(good[1].getVolume()));
        good3CapacityText.setText(String.valueOf(good[2].getVolume()));
        good1TechLevelText.setText(String.valueOf(good[0].getTechLevel()));
        good2TechLevelText.setText(String.valueOf(good[1].getTechLevel()));
        good3TechLevelText.setText(String.valueOf(good[2].getTechLevel()));
        updateCharacterInfo();
    }
    public void updateCharacterInfo(){
        playerAndShipNameText.setText(String.format("%s(%s)",player.getName(), ship.getName()));
        balanceText.setText(String.format("Balance: %d",player.getBalance()));
        capacityText.setText(String.valueOf(String.format("Capacity: %d",ship.getCargoCapacity())));
        good1InfoText.setText(String.format("Good1: %d", ship.getItemInventory()[0].getQuantity()));
        good2InfoText.setText(String.format("Good2: %d", ship.getItemInventory()[1].getQuantity()));
        good3InfoText.setText(String.format("Good3: %d", ship.getItemInventory()[2].getQuantity()));
    }
    public void resetSpinner(){
        good1Spinner.getValueFactory().setValue(0);
        good2Spinner.getValueFactory().setValue(0);
        good3Spinner.getValueFactory().setValue(0);
    }
    public void characterUpgradeBtnPressed(ActionEvent actionEvent) {
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
        if (totalCapacity < ship.getCargoCapacity() && totalCost < player.getBalance()) {
            // if they are enough, update balance, capacity and add items to the ship
            player.setBalance(player.getBalance() - totalCost);
            ship.setCargoCapacity(ship.getCargoCapacity() - totalCapacity);

            if (good1Spinner.getValue() != 0) {
                shipInventory[0].setQuantity(shipInventory[0].getQuantity()+good1Spinner.getValue());
            }
            if (good2Spinner.getValue() != 0) {
                shipInventory[1].setQuantity(shipInventory[1].getQuantity()+good2Spinner.getValue());
            }
            if (good3Spinner.getValue() != 0) {
                shipInventory[2].setQuantity(shipInventory[2].getQuantity()+good3Spinner.getValue());
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
        int wts1 = good1Spinner.getValue();
        int wts2 = good2Spinner.getValue();
        int wts3 = good3Spinner.getValue();
        // check if ship have enough items to sell
        if (wts1 <= shipInventory[0].getQuantity() && wts2 <= shipInventory[1].getQuantity() &&wts3 <= shipInventory[2].getQuantity()) {
            // update player's balance and ship's capacity and inventory
            int totalRevenue =(int) discount*( wts1*good[0].getPrice()+ wts2*good[1].getPrice()+wts3*good[2].getPrice());
            int totalGainCapacity = wts1*good[0].getVolume()+wts2*good[1].getVolume()+wts3*good[2].getVolume();
            player.setBalance(player.getBalance()+totalRevenue);
            ship.setCargoCapacity(ship.getCargoCapacity()+totalGainCapacity);
            shipInventory[0].setQuantity(shipInventory[0].getQuantity()-wts1);
            shipInventory[1].setQuantity(shipInventory[1].getQuantity()-wts2);
            shipInventory[2].setQuantity(shipInventory[2].getQuantity()-wts3);
            ship.setItemInventory(shipInventory);
        }
        else{
            errorMessage.setText("You don't have enough items to sell!");
        }
        //reset spinner
        resetSpinner();
        // update UI of character's info
        updateCharacterInfo();
    }
}
