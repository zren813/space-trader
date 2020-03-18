import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.event.IIOWriteProgressListener;
import java.io.IOException;

public class PoliceViewController {
    @FXML
    private Text introText;
    private Police police;

    public void initialize() {
        police = new Police();
        introText.setText(police.getDescription());
    }

    public void buyItemsBtnPressed(ActionEvent actionEvent) throws IOException {
        String result = police.acceptStolenGood();
        resultDialog(result);
        goToMapView(actionEvent);
    }

    public void fleeBackBtnPressed(ActionEvent actionEvent) throws IOException {
        String result = police.dealWithFleeingPlayer();
        resultDialog(result);
        goToMapView(actionEvent);
    }

    public void fightOffBtnPressed(ActionEvent actionEvent) throws IOException {
        String result = police.dealWithFightingOff();
        resultDialog(result);
        goToMapView(actionEvent);
    }

    public void goToMapView(ActionEvent actionEvent) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("MapView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void resultDialog(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Trader");
        alert.setHeaderText("Result");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
