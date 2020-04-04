import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BanditViewController {
    @FXML
    private Text introText;
    @FXML
    private Bandit bandit;

    @FXML
    public void initialize() {
        bandit = new Bandit();
        introText.setText(bandit.getDescription());
    }

    public void payMoneyBtnPressed(ActionEvent actionEvent) throws IOException {
        String message = bandit.chargeCredit(MapViewController.getPlayer(),
                MapViewController.getPlayer().getShip());
        resultDialog(message);
        goToNextView(actionEvent);
    }

    public void fleeBackBtnPressed(ActionEvent actionEvent) throws IOException {
        String message = bandit.dealWithFleeingPlayer(MapViewController.getPlayer(),
                MapViewController.getPlayer().getShip());
        resultDialog(message);
        goToNextView(actionEvent);
    }

    public void fightOffBtnPressed(ActionEvent actionEvent) throws IOException {
        String message = bandit.fightWithPlayer(MapViewController.getPlayer(),
                MapViewController.getPlayer().getShip());
        resultDialog(message);
        goToNextView(actionEvent);
    }

    public void goToNextView(ActionEvent actionEvent)throws IOException {
        String viewName;
        if(MapViewController.getShip().getHealth() <= 0){
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
        alert.setTitle("Bandit");
        alert.setHeaderText("Result");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
