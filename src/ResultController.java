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
    private Text title;
    @FXML
    private Button restartBtn;
    @FXML
    private Button exitBtn;

    private Player player = MapViewController.getPlayer();

    public void initialize() {
        int health = player.getShip().getHealth();
        if (health < 0) {
            health = 0;
        }
        title.setText("Game made by Team 20 of CS 2340 in Spring 2020 at Georgia Tech\n"
                + "Credit:\n"
                + "        Jingyi Wan\n"
                + "        Chiche Tsai\n"
                + "        Ziyao Ren\n"
                + "        Kehan Wang\n"
                + "        Qifan Yang");
        introText.setText("The balance you currently own is " + player.getBalance() + ".\n"
                + "Ship's Health is " + health + ".\n"
                + "Do you want to restart a new game?");
    }

    public void restartBtnPressed(ActionEvent actionEvent) throws Exception {
        MapViewController.setIsOpened(false);

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
