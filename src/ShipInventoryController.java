import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class ShipInventoryController {
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
    public Text good1Quantity;
    @FXML
    public Text good2Quantity;
    @FXML
    public Text good3Quantity;
    @FXML
    public Text good4Quantity;
    @FXML
    public Text good5Quantity;
    @FXML
    public Text good6Quantity;
    @FXML
    public Text good7Quantity;
    @FXML
    public Text good8Quantity;
    @FXML
    public Text good9Quantity;
    @FXML
    public Text good10Quantity;
    @FXML
    public Text good11Quantity;
    @FXML
    public Text good12Quantity;
    @FXML
    public Text good13Quantity;
    @FXML
    public Text good14Quantity;
    @FXML
    public Text good15Quantity;
    
    @FXML
    private Text playerInfoText;

    @FXML
    private Text goodNameText[];
    private Text goodCapacityText[];
    private Text goodQuantityText[];

    private Good good[];

    private Player player;
    private Good[] shipInventory;
    private int numberOfGood;
    private static GoodGenerater goodGenerater;
    private static boolean isopened = false;

    public void initialize() {
        Random random = new Random();

        goodNameText = new Text[]{good1NameText, good2NameText, good3NameText, good4NameText, good5NameText,
                good6NameText, good7NameText, good8NameText, good9NameText, good10NameText, good11NameText,
                good12NameText, good13NameText, good14NameText, good15NameText};
        goodCapacityText = new Text[]{good1CapacityText, good2CapacityText, good3CapacityText, good4CapacityText,
                good5CapacityText, good6CapacityText, good7CapacityText, good8CapacityText, good9CapacityText,
                good10CapacityText, good11CapacityText, good12CapacityText, good13CapacityText, good14CapacityText,
                good15CapacityText};
        goodQuantityText = new Text[]{good1Quantity, good2Quantity, good3Quantity, good4Quantity, good5Quantity,
                good6Quantity, good7Quantity, good8Quantity, good9Quantity, good10Quantity, good11Quantity,
                good12Quantity, good13Quantity, good14Quantity, good15Quantity};

        // initialize goods and assign good, player and the ship
//        if (!isopened){
//            isopened = true;
//            goodGenerater = new GoodGenerater();
//        }
        numberOfGood = goodGenerater.getNumberOfGood();
        good = goodGenerater.getGood();
        player = MapController.getPlayer();
        shipInventory = MapController.getPlayer().getShip().getItemInventory();

        // set up layout of the UI
        updateGoodInfo();
        updateCharacterInfo();
    }

    public void updateGoodInfo() {
        // update all ui texts
        for (int i = 0; i < numberOfGood; i++) {
            goodNameText[i].setText(good[i].getName());
            goodCapacityText[i].setText(String.valueOf(good[i].getVolume()));
            goodQuantityText[i].setText(String.valueOf(shipInventory[i].getQuantity()));
        }
    }

    public void updateCharacterInfo() {
        String playerInfo = "";
        playerInfo += String.format("%s(%s)", player.getName(), player.getShip().getName()) +"\n";
        playerInfo += String.format( "Balance: %d", player.getBalance()) +"\n";
        playerInfo += String.format("Ship Capacity: %d", player.getShip().getCargoCapacity()) +"\n";
        playerInfo += String.format("Ship Health: %d", player.getShip().getHealth()) +"\n";

        playerInfoText.setText(playerInfo);
    }


    public void exitBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("Market.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

}
