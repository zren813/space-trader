import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.text.Text;

public class MarketController {
    @FXML
    private Text good1Text;
    @FXML
    private Text good2Text;
    @FXML
    private Text good3Text;
    @FXML
    private Text good1PriceText;
    @FXML
    private Text good2PriceText;
    @FXML
    private Text good3PriceText;
    @FXML
    private Spinner Good1Spinner;
    @FXML
    private Spinner Good2Spinner;
    @FXML
    private Spinner Good3Spinner;

    private Good good[];

    public void initialize(){
        GoodGenerater goodGenerater = new GoodGenerater();
        good = goodGenerater.getGood();
        good1Text.setText(good[0].getName());
        good2Text.setText(good[1].getName());
        good3Text.setText(good[2].getName());
        good1PriceText.setText("$"+good[0].getBase_price());
        good2PriceText.setText("$"+good[1].getBase_price());
        good3PriceText.setText("$"+good[2].getBase_price());

    }

    public void characterUpgradeBtnPressed(ActionEvent actionEvent) {
    }

    public void exitBtnPressed(ActionEvent actionEvent) {
    }

    public void good1BuyBtnPressed(ActionEvent actionEvent) {
    }

    public void good1SellBtnPressed(ActionEvent actionEvent) {
    }

    public void good2BuyBtnPressed(ActionEvent actionEvent) {
    }

    public void good2SellBtnPressed(ActionEvent actionEvent) {
    }

    public void good3BuyBtnPressed(ActionEvent actionEvent) {
    }

    public void good3SellBtnPressed(ActionEvent actionEvent) {
    }
}
