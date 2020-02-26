import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

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
    private Text textInfo;
    @FXML
    private Button exploreBtn;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label hereLabel;

    private Circle[] circleArray = new Circle[10];
    private Tooltip[] toolTipArray = new Tooltip[10];
    private Planet[] planetArray = new Planet[10];
    private static WorldGenerator worldGenerator;
    private static boolean opened;
    private static Player player;
    private int yRange;
    private int xRange;
    private int xOffset;
    private int yOffset;
    private static int planetClicked = 0;


    @FXML
    public void initialize() {

        xRange = (int) rectangle.getWidth() - 25;
        yRange = (int) rectangle.getHeight() - 40;
        xOffset = (int) rectangle.getLayoutX() - 60;
        yOffset = (int) rectangle.getLayoutY() + 10;
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
        if (!opened) {
            worldGenerator = new WorldGenerator();
            player = new Player();
        }
        worldGenerator = MapController.getWorldGenerator();
        planetArray = worldGenerator.getPlanetArray();
        if(!opened){fixPlanetCoordinates();}
        player = MapController.getPlayer();
        updatePlayerInfo();
        for (int i = 0; i < 10; i++) {
            toolTipArray[i] = new Tooltip();
            circleArray[i].setCenterX(planetArray[i].getXCoordinate());
            circleArray[i].setCenterY(planetArray[i].getYCoordinate());
            circleArray[i].setFill(planetArray[i].getPaint());
            toolTipArray[i].setText(planetArray[i].displayInfo() + "\n" 
                + "Distance: " + player.getDistanceArray()[i] + "\n" + "[" 
                + planetArray[i].getXCoordinate() + ", " 
                + planetArray[i].getYCoordinate() + "]");
            Tooltip.install(circleArray[i], toolTipArray[i]);
        }
        hereLabel.setBackground(new Background(
                new BackgroundFill(
                        Color.hsb(25, 0.5, 0.5), 
                        CornerRadii.EMPTY, Insets.EMPTY)));
        hereLabel.setLayoutY(player.getCurrentPlanet().getYCoordinate());
        hereLabel.setLayoutX(player.getCurrentPlanet().getXCoordinate() - 25);
    }
    public void updatePlayerInfo(){
        String playerInfo = "Balance: "+ player.getBalance() + "\n"
            + "Pilot skill point: " + player.getPilotSkill() + "\n"
            + "Fighter skill point: " +player.getFighterSkill()+ "\n"
            + "Merchant skill point: " + player.getMerchantSkill() + "\n"
            + "Engineer skill point:  " + player.getEngineerSkill();
        textInfo.setText(playerInfo);
    }
    public static WorldGenerator getWorldGenerator() {
        return worldGenerator;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setOpened(boolean isOpened) {
        opened = isOpened;
    }

    public void fixPlanetCoordinates() {
        for (int i = 0; i < 10; i++) {
            planetArray[i].setxCoordinate(
                generateCoordinates(xOffset + xRange / 10 * (i + 1), xRange / 10)
            );
            planetArray[i].setyCoordinate(
                generateCoordinates(yOffset, yRange)
            );
            if (i != 0 && Math.abs(planetArray[i].getYCoordinate() 
                    - planetArray[i - 1].getYCoordinate()) < 35) {
                i--;
            }
        }
    }

    public int generateCoordinates(int offset, int range) {
        Random random = new Random();
        return random.nextInt(range) + offset;
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
    public void explore1BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 1;
        player.setCurrentPlanet(planetArray[1]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore2BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 2;
        player.setCurrentPlanet(planetArray[2]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore3BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 3;
        player.setCurrentPlanet(planetArray[3]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore4BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 4;
        player.setCurrentPlanet(planetArray[4]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore5BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 5;
        player.setCurrentPlanet(planetArray[5]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore6BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 6;
        player.setCurrentPlanet(planetArray[6]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore7BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 7;
        player.setCurrentPlanet(planetArray[7]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore8BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 8;
        player.setCurrentPlanet(planetArray[8]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore9BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 9;
        player.setCurrentPlanet(planetArray[9]);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public void explore0BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 0;
        player.setCurrentPlanet(planetArray[0]);
        setHereLabel(
                player.getCurrentPlanet().getYCoordinate(), 
                player.getCurrentPlanet().getXCoordinate() - 20);
        worldGenerator.setPlanetArray(planetArray);
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
    public static int getPlanetClicked() {
        return planetClicked;
    }

    public void setHereLabel(int x, int y) {
        hereLabel.setLayoutX(x);
        hereLabel.setLayoutY(y);
    }
}
