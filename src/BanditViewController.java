import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BanditViewController {
    @FXML
    public Text intro;
    @FXML
    private Text message;
    @FXML
    public Button payBanditBtn;
    
    private Bandit bandit;

    @FXML
    public void initialize() {
        bandit = new Bandit(MapViewController.getPrevPlanet(), MapViewController.getCurrPlanet());
        intro.setText(bandit.encounterBandit());
        message.setText("");
        
    }
    
    public void payBanditBtnPressed(ActionEvent event) throws IOException {
        
        message.setText(
                bandit.payBandit(
                        MapViewController.getPlayer(),
                        MapViewController.getPlayer().getShip())
                );
        goToPlanet(event);
    }
    
    public void goToPlanet(ActionEvent event) throws IOException {
        
        //desired = true, go to desiredPlanet
        //desired = false, go to previousPlanet
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
