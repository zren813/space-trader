import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PlanetViewController {
    @FXML
    CubicCurve planetViewCurve;
    @FXML
    Text name;
    @FXML
    Text technologyLevel;
    @FXML
    Text color;
    @FXML
    Text discription;

    private WorldGenerator worldGenerator;
    private Planet[] planetArray;
    private int index;


    @FXML
    public void initialize() {
        worldGenerator = MapController.getWorldGenerator();
        planetArray = worldGenerator.getPlanetArray();
        for (int i = 0; i < 10; i++) {
            planetArray[i] = new Planet();
        }
        index = 0;
        planetViewCurve.setFill(planetArray[index].getPaint());
        name.setText(planetArray[index].getName());
        technologyLevel.setText(String.valueOf(planetArray[index].getTechnologyLevel()));
        color.setText(planetArray[index].getPaint().toString());
        discription.setText(planetArray[index].getDescription());
    }

    public void returnBtnPressed(ActionEvent event) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("Map.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void prevBtnPressed(ActionEvent event) throws IOException {
        index = (index - 1) % planetArray.length;
        if (index < 0) {
            index += planetArray.length;
        }
        planetViewCurve.setFill(planetArray[index].getPaint());
        name.setText(planetArray[index].getName());
        technologyLevel.setText(String.valueOf(planetArray[index].getTechnologyLevel()));
        color.setText(planetArray[index].getPaint().toString());
        discription.setText(planetArray[index].getDescription());
    }

    public void nextBtnPressed(ActionEvent event) throws IOException {
        index = (index + 1) % planetArray.length;
        planetViewCurve.setFill(planetArray[index].getPaint());
        name.setText(planetArray[index].getName());
        technologyLevel.setText(String.valueOf(planetArray[index].getTechnologyLevel()));
        color.setText(planetArray[index].getPaint().toString());
        discription.setText(planetArray[index].getDescription());
    }
}
