import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class ResultController {
    @FXML
    private Text introText;
    @FXML
    private Button restartBtn;
    @FXML
    private Button exitBtn;

    private Player player = MapViewController.getPlayer();

    public void initialize() {
        introText.setText("The balance you currently own is " + player.getBalance() + ".\n" +
                "Do you want to restart a new game?");
    }

    public void restartBtnPressed(ActionEvent actionEvent) throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("WelcomeView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void exitBtnPressed(ActionEvent actionEvent) throws Exception {
        System.exit(0);
    }
}
