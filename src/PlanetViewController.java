import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PlanetViewController {
    @FXML
    private CubicCurve planetViewCurve;
    @FXML
    private Text planetNameText;
    @FXML
    private Text technologyLevelText;
    @FXML
    private Text distanceText;
    @FXML
    private Text fuelCostText;
    @FXML
    private Text descriptionText;
    @FXML
    private Text errorMessage;

    private PlanetGenerator planetGenerator;
    private Planet[] planetArray;
    private int whichPlanetViewed;


    @FXML
    public void initialize() {
        planetGenerator = MapViewController.getPlanetGenerator();
        planetArray = MapViewController.getPlanetArray();
        whichPlanetViewed = MapViewController.getPlanetClickedID();
        planetArray[whichPlanetViewed].setVisited(true);
        Player.setCurrentPlanet(planetArray[whichPlanetViewed]);
        updateUI();
    }

    private void updateUI() {
        planetNameText.setText(planetArray[whichPlanetViewed].getName());
        distanceText.setText(planetGenerator.getDistanceArray()[whichPlanetViewed]+" light-years");
        fuelCostText.setText(planetGenerator.getDistanceArray()[whichPlanetViewed]/10.0+" gallons");
        technologyLevelText.setText(String.valueOf(planetArray[whichPlanetViewed].getTechnologyLevel()));
        descriptionText.setText(planetArray[whichPlanetViewed].displayInfo());
        planetViewCurve.setFill(planetArray[whichPlanetViewed].getPaint());
        errorMessage.setText("");
    }

    public void exitBtnPressed(ActionEvent event) throws IOException {
        PlanetGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("MapView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void marketBtnPressed(ActionEvent event) throws IOException {
        if(planetArray[whichPlanetViewed].isVisited()){
            goToMarketView(event);
        }else{
            errorMessage.setText("You haven't visited this planet!");
        }
    }
    public void goToMarketView(ActionEvent event)throws  IOException{
        Parent configParent = FXMLLoader.load(getClass().getResource("MarketView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
