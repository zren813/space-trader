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
    private Text name;
    @FXML
    private Text technologyLevel;
    @FXML
    private Text color;
    @FXML
    private Text description;

    private static WorldGenerator worldGenerator;
    private static int indexPass;
    private Planet[] planetArray;
    private int index;
    private static Player player;


    @FXML
    public void initialize() {
        worldGenerator = MapController.getWorldGenerator();
        player = MapController.getPlayer();
        planetArray = worldGenerator.getPlanetArray();
        index = MapController.getPlanetClicked();
        planetViewCurve.setFill(player.getCurrentPlanet().getPaint());
        name.setText(player.getCurrentPlanet().getName());
        technologyLevel.setText(String.valueOf(player.getCurrentPlanet().getTechnologyLevel()));
        color.setText(player.getCurrentPlanet().getPaint().toString());
        description.setText(player.getCurrentPlanet().getDescription());
        player.getCurrentPlanet().setVisited(true);
    }

    public void returnBtnPressed(ActionEvent event) throws IOException {
        worldGenerator.setPlanetArray(planetArray);
        MapController.setOpened(true);
        Parent configParent = FXMLLoader.load(getClass().getResource("Map.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(configScene);
        window.show();
    }

    public void prevBtnPressed(ActionEvent event) throws IOException {
        index = Math.abs((index - 1) % planetArray.length);
        player.setCurrentPlanet(planetArray[index]);
        if (index < 0) {
            index += planetArray.length;
        }
        planetViewCurve.setFill(planetArray[index].getPaint());
        name.setText(planetArray[index].getName());
        technologyLevel.setText(String.valueOf(planetArray[index].getTechnologyLevel()));
        color.setText(planetArray[index].getPaint().toString());
        description.setText(planetArray[index].getDescription());
        planetArray[index].setVisited(true);
    }

    public void nextBtnPressed(ActionEvent event) throws IOException {
        index = (index + 1) % planetArray.length;
        player.setCurrentPlanet(planetArray[index]);
        planetViewCurve.setFill(planetArray[index].getPaint());
        name.setText(planetArray[index].getName());
        technologyLevel.setText(String.valueOf(planetArray[index].getTechnologyLevel()));
        color.setText(planetArray[index].getPaint().toString());
        description.setText(planetArray[index].getDescription());
        planetArray[index].setVisited(true);
    }

    public void marketBtnPressed(ActionEvent event) throws IOException {
        indexPass = index;

        Parent configParent = FXMLLoader.load(getClass().getResource("Market.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public static WorldGenerator getWorldGenerator() {
        return worldGenerator;
    }

    public static int getIndexPass() {
        return indexPass;
    }
}
