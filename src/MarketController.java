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
    private Text errorMessage;

    private Good good[];
    private Player player;
    private Ship ship;
    private double discount = 0.8;

    public void initialize() {
        // initialize goods and assign good, player and the ship
        player = MapController.getPlayer();
        ship = player.getShip();
        GoodGenerater goodGenerater = new GoodGenerater();
        good = goodGenerater.getGood();
        // set up layout of the UI
        good1NameText.setText(good[0].getName());
        good2NameText.setText(good[1].getName());
        good3NameText.setText(good[2].getName());
        good1PriceText.setText("$" + good[0].getPrice() + "/$" + (int)(good[0].getPrice() * discount));
        good2PriceText.setText("$" + good[1].getPrice() + "/$" + (int)(good[1].getPrice() * discount));
        good3PriceText.setText("$" + good[2].getPrice() + "/$" + (int)(good[2].getPrice() * discount));
        good1CapacityText.setText(String.valueOf(good[0].getCapacity()));
        good2CapacityText.setText(String.valueOf(good[1].getCapacity()));
        good3CapacityText.setText(String.valueOf(good[2].getCapacity()));
        good1TechLevelText.setText(String.valueOf(good[0].getTechLevel()));
        good2TechLevelText.setText(String.valueOf(good[1].getTechLevel()));
        good3TechLevelText.setText(String.valueOf(good[2].getTechLevel()));
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

            Good boughtgood[] = new Good[GoodGenerater.getNumberOfGood()];

            if (good1Spinner.getValue() != 0) {
                boughtgood[0] = new Good(good[0].getName(), good[0].getTechLevel(), good[0].getCapacity());
            }
            if (good2Spinner.getValue() != 0) {
                boughtgood[1] = new Good(good[1].getName(), good[1].getTechLevel(), good[1].getCapacity());
            }
            if (good1Spinner.getValue() != 0) {
                boughtgood[2] = new Good(good[2].getName(), good[2].getTechLevel(), good[2].getCapacity());
            }
            ship.setItemInventory(boughtgood);
            // printout for debugging
            System.out.println(player.getBalance());
            System.out.println(ship.getCargoCapacity());
        } else {
            errorMessage.setText("You don't have enough capacity/balance!");
        }

    }

    public void good1SellBtnPressed(ActionEvent actionEvent) {

    }
}
