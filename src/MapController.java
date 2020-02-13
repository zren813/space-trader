import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MapController {
    @FXML
    private Circle planet1;
    @FXML
    private Circle planet2;
    @FXML
    private Circle planet3;
    @FXML
    private Circle planet4;
    @FXML
    private Circle planet5;
    @FXML
    private Circle planet6;
    @FXML
    private Circle planet7;
    @FXML
    private Circle planet8;
    @FXML
    private Circle planet9;
    @FXML
    private Circle planet10;
    @FXML
    private Text TextInfo;
    @FXML
    private Button exploreBtn;

    private Circle[] circleArray = new Circle[10];
    private Tooltip[] toolTipArray = new Tooltip[10];
    private Planet[] planetArray = new Planet[10];
    private static WorldGenerator worldGenerator;
    private static boolean opened;
    private static Player player;


    @FXML
    public void initialize() {
        TextInfo.setText("Credits: to be continue" + "\n" +
                "Pilot skill point: " + ConfigController.getNumPilotSP() + "\n" +
                "Fighter skill point: " + ConfigController.getNumFighterSP() + "\n" +
                "Merchant skill point: " + ConfigController.getNumMerchantSP() + "\n" +
                "Engineer skill point:  " + ConfigController.getNumEngineerSP());

        circleArray[0] = planet1;
        circleArray[1] = planet2;
        circleArray[2] = planet3;
        circleArray[3] = planet4;
        circleArray[4] = planet5;
        circleArray[5] = planet6;
        circleArray[6] = planet7;
        circleArray[7] = planet8;
        circleArray[8] = planet9;
        circleArray[9] = planet10;
        if (opened) {
            worldGenerator = MapController.getWorldGenerator();
            player = MapController.getPlayer();
        } else {
            worldGenerator = new WorldGenerator();
            player = new Player();
        }
        planetArray = worldGenerator.getPlanetArray();
        for (int i = 0; i < 10; i++) {
            toolTipArray[i] = new Tooltip();
            circleArray[i].setCenterX(planetArray[i].getXCoordinate());
            circleArray[i].setCenterY(planetArray[i].getYCoordinate());
            circleArray[i].setFill(planetArray[i].getPaint());
            toolTipArray[i].setText(planetArray[i].displayInfo() + "\n" +"Distance: " + player.getDistanceArray()[i] +
                    "\n" + "[" + planetArray[i].getXCoordinate() + ", " + planetArray[i].getYCoordinate() + "]");
            Tooltip.install(circleArray[i],toolTipArray[i]);
        }
    }

    public static WorldGenerator getWorldGenerator() {
        return worldGenerator;
    }
    public static Player getPlayer() {return player; }
    public static void setOpened(boolean isOpened) {
        opened = isOpened;
    }

    public void exploreBtnPressed(ActionEvent event) throws IOException {
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void jumpBtnPressed(ActionEvent event) throws IOException {
        MapController.setOpened(false);
        Parent configParent = FXMLLoader.load(getClass().getResource("Map.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
