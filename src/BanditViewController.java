import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BanditViewController {
    @FXML
    public Text intro;
    @FXML
    private Bandit bandit;

    @FXML
    public void initialize() {
        bandit = new Bandit();
        intro.setText(bandit.getDescription());
    }

    public void payMoneyBtnPressed(ActionEvent actionEvent) throws IOException {
        String message = bandit.chargeCredit(MapViewController.getPlayer(), MapViewController.getPlayer().getShip());
        resultDialog(message);
        goToMapView(actionEvent);
    }

    public void fleeBackBtnPressed(ActionEvent actionEvent) throws IOException {
        String message = bandit.dealWithFleeingPlayer(MapViewController.getPlayer(), MapViewController.getPlayer().getShip());
        resultDialog(message);
        goToMapView(actionEvent);
    }

    public void fightOffBtnPressed(ActionEvent actionEvent) throws IOException {
        String message = bandit.fightWithPlayer(MapViewController.getPlayer(), MapViewController.getPlayer().getShip());
        resultDialog(message);
        goToMapView(actionEvent);
    }

    public void goToMapView(ActionEvent event) throws IOException {

        //desired = true, go to desiredPlanet
        //desired = false, go to previousPlanet
        Parent configParent = FXMLLoader.load(getClass().getResource("MapView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void resultDialog(String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Bandit");
        alert.setHeaderText("Result");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
