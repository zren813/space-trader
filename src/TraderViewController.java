import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class TraderViewController {
    @FXML
    private Button negotiateBtn;
    @FXML
    private Text introText;
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
    private Text good1VolumeText;
    @FXML
    private Text good2VolumeText;
    @FXML
    private Text good3VolumeText;
    @FXML
    private Spinner<Integer> good1Spinner;
    @FXML
    private Spinner<Integer> good2Spinner;
    @FXML
    private Spinner<Integer> good3Spinner;
    private Text[] goodNameText;
    private Text[] goodPriceText;
    private Text[] goodVolumeText;


    private Trader trader;
    private Player player;
    private Ship ship;

    @FXML
    public void initialize() {
        trader = new Trader();
        player = MapViewController.getPlayer();
        ship = player.getShip();
        introText.setText(trader.getDescription());
        setUpUI();
    }

    private void setUpUI() {
        Good[] traderGood = trader.getTraderGood();
        goodNameText = new Text[]{good1NameText, good2NameText, good3NameText};
        goodPriceText = new Text[]{good1PriceText, good2PriceText, good3PriceText};
        goodVolumeText = new Text[]{good1VolumeText, good2VolumeText, good3VolumeText};
        for (int i = 0; i < goodNameText.length; i++) {
            goodNameText[i].setText(traderGood[i].getName());
            goodPriceText[i].setText(traderGood[i].getBasePrice() + "");
            goodVolumeText[i].setText(traderGood[i].getVolume() + "");
        }
    }

    public void buyItemsBtnPressed(ActionEvent actionEvent) throws IOException {
        int[] numOfGoodToBuy = new int[]{good1Spinner.getValue(),
                good2Spinner.getValue(), good3Spinner.getValue()};
        String result = trader.sellGoodsToPlayer(player, ship, numOfGoodToBuy);
        resultDialog(result);
        goToNextView(actionEvent);
    }

    public void ignoreBtnPressed(ActionEvent actionEvent) throws IOException {
        goToNextView(actionEvent);
    }

    public void robTraderBtnPressed(ActionEvent actionEvent) throws IOException {
        String result = trader.getRobbed(player, ship);
        resultDialog(result);
        goToNextView(actionEvent);
    }

    public void negotiateBtnPressed(ActionEvent actionEvent) {
        String result = trader.getNegotiated(player);
        resultDialog(result);
        setUpUI();
        negotiateBtn.setVisible(false);
    }


    public void goToNextView(ActionEvent actionEvent)throws IOException {
        String viewName;
        if(MapViewController.getShip().getHealth() < 0){
            viewName = "WelcomeView";
            gameOverDialog();
        }else{
            viewName = "MapView";
        }
        Parent configParent = FXMLLoader.load(getClass().getResource(viewName+".fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void gameOverDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!!");
        alert.setHeaderText("Game Over");
        alert.setContentText("Your ship's health is below 0. Restart a new game?");
        ButtonType buttonTypeOne = new ButtonType("Restart");
        ButtonType buttonTypeTwo = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
//            goToWelcomeView();
        } else if (result.get() == buttonTypeTwo) {
            System.exit(0);
        }
    }

    public void resultDialog(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Trader");
        alert.setHeaderText("Result");
        alert.setContentText(content);
        alert.showAndWait();
    }


}
