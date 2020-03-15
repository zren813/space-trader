import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Text playerInfoText;
    @FXML
    private Text skillInfoText;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label hereLabel;
    @FXML
    private Label planet1NameLabel;
    @FXML
    private Label planet2NameLabel;
    @FXML
    private Label planet3NameLabel;
    @FXML
    private Label planet4NameLabel;
    @FXML
    private Label planet5NameLabel;
    @FXML
    private Label planet6NameLabel;
    @FXML
    private Label planet7NameLabel;
    @FXML
    private Label planet8NameLabel;
    @FXML
    private Label planet9NameLabel;
    @FXML
    private Label planet10NameLabel;
    @FXML
    private Text errorMessage;

    private Circle[] circleArray;
    private Tooltip[] toolTipArray;
    private Label planetNameLabel[];

    private static PlanetGenerator planetGenerator;
    private static Planet[] planetArray;
    private static Player player;
    private static Ship ship;
    private static GoodGenerater goodGenerater;
    private static Good[] good;
    private static Good[] shipInventory;
    private static boolean isOpened = false;
    private static int planetClicked;


    @FXML
    public void initialize() {
        // First time, generate planets
        if (!isOpened) {
            isOpened = true;
            // setup planets
            planetGenerator = new PlanetGenerator();
            planetArray = planetGenerator.getPlanetArray();
            fixPlanetCoordinates();

            //get player and ship
            player = ConfigController.getPlayer();
            initializePlayerPlanet();

            //set up good and ship's goodInventory
            ship = player.getShip();
            initializeGood();
        }

        setUpUIObject();
        setUpMap();
        updateInfo();
        planetClicked = player.getCurrentPlanet().getPlanetID();
    }

    private void initializeGood() {
        goodGenerater = new GoodGenerater();
        good = goodGenerater.getGood();
        shipInventory = new Good[GoodGenerater.getNumberOfGood()];
        for (int i = 0; i < GoodGenerater.getNumberOfGood(); i++) {
            shipInventory[i] = new Good(good[i].getName(), good[i].getBasePrice(),
                good[i].getVolume(), good[i].getTechLevel());
        }
        ship.setItemInventory(shipInventory);
        player.setShip(ship);
    }

    private void initializePlayerPlanet() {
        Random random = new Random();
        int initialPlanetID = random.nextInt(planetArray.length);
        player.setCurrentPlanet(planetArray[initialPlanetID]);
        planetArray[initialPlanetID].setVisited(true);
    }


    private void setUpUIObject() {
        circleArray = new Circle[]{planet1, planet2, planet3, planet4, planet5,
            planet6, planet7, planet8, planet9, planet10};
        planetNameLabel = new Label[]{planet1NameLabel, planet2NameLabel, planet3NameLabel, planet4NameLabel,
            planet5NameLabel, planet6NameLabel, planet7NameLabel, planet8NameLabel, planet9NameLabel, planet10NameLabel};
        toolTipArray = new Tooltip[circleArray.length];
    }

    private void setUpMap() {
        setUpCircle();
        setUpToolTip();
        setUpHereLabel();
        setUpNameLabel();
        errorMessage.setText("");
    }

    private void setUpToolTip() {
        for (int i = 0; i < planetArray.length; i++) {
            String toolTipMessage = "";
            if (planetArray[i].isVisited()) {
                toolTipMessage = String.format("Name: %s\n", planetArray[i].getName())
                    + String.format("Coordinate: (%d, %d)\n", planetArray[i].getXCoordinate(), planetArray[i].getYCoordinate())
                    + String.format("Distance: %d light-years\n", + planetGenerator.getDistanceArray(player.getCurrentPlanet())[i])
                    + String.format("Fuel Needed: %.1f gallons\n", (float) planetGenerator.getDistanceArray(player.getCurrentPlanet())[i] / 10)
                    + String.format("Tech Level: %d", planetArray[i].getTechnologyLevel());
            } else {
                toolTipMessage = String.format("Name: %s\n", planetArray[i].getName())
                    + String.format("Coordinate: (%d, %d)\n", planetArray[i].getXCoordinate(), planetArray[i].getYCoordinate())
                    + String.format("Distance: %d light-years\n", +planetGenerator.getDistanceArray(player.getCurrentPlanet())[i])
                    + String.format("Fuel Needed: %.1f gallons\n", (float) planetGenerator.getDistanceArray(player.getCurrentPlanet())[i] / 10)
                    + String.format("Tech Level: unknown -__-||");
            }
            toolTipArray[i] = new Tooltip(toolTipMessage);
            Tooltip.install(circleArray[i], toolTipArray[i]);
        }
    }

    private void setUpCircle() {
        for (int i = 0; i < planetArray.length; i++) {
            circleArray[i].setCenterX(planetArray[i].getXCoordinate());
            circleArray[i].setCenterY(planetArray[i].getYCoordinate());
            circleArray[i].setFill(planetArray[i].getPaint());
        }
    }

    // FIX ME
    private void setUpNameLabel() {
        for (int i = 0; i < planetNameLabel.length; i++) {
            planetNameLabel[i].setText(planetArray[i].getName());
            planetNameLabel[i].setLayoutX(planetArray[i].getXCoordinate() - 20);
            planetNameLabel[i].setLayoutY(planetArray[i].getYCoordinate() - 40);
        }
    }

    public void setUpHereLabel() {
        Background background = new Background(new BackgroundFill(
            Color.hsb(25, 0.5, 0.5),
            CornerRadii.EMPTY, Insets.EMPTY));
        hereLabel.setBackground(background);
        hereLabel.setLayoutX(player.getCurrentPlanet().getXCoordinate()-20);
        hereLabel.setLayoutY(player.getCurrentPlanet().getYCoordinate()+20);
    }

    public void updateInfo() {
        String playerInfo =
            "Player: " + player.getName() + '\n'
                + "Balance: " + player.getBalance() + '\n'
                + "Ship fuel: " + player.getShip().getFuelCapacity() + '\n'
                + "Ship capacity: " + player.getShip().getCargoCapacity() + '\n'
                + "Ship health: " + player.getShip().getHealth();
        playerInfoText.setText(playerInfo);
        String skillInfo =
            "Pilot skill point: " + player.getPilotSkill() + '\n'
                + "Fighter skill point: " + player.getFighterSkill() + '\n'
                + "Merchant skill point: " + player.getMerchantSkill() + '\n'
                + "Engineer skill point:  " + player.getEngineerSkill();
        skillInfoText.setText(skillInfo);
    }


    // 14x7 grid and randomly generate coordinates for planets
    //  won't be overlap
    public void fixPlanetCoordinates() {
        Set<Integer> xGrid = new HashSet<>();
        Set<Integer> yGrid = new HashSet<>();
        int width = 60;
        int height = 60;
        Random random = new Random();
        int recLayoutX = (int) rectangle.getLayoutX() + 20;
        int recLayoutY = (int) rectangle.getLayoutY() + 20;
        for (int i = 0; i < planetArray.length; i++) {
            int X = random.nextInt(14);
            int Y = random.nextInt(7);
            while (xGrid.contains(X) && yGrid.contains(Y)) {
                X = random.nextInt(14);
                Y = random.nextInt(7);
            }
            xGrid.add(X);
            yGrid.add(Y);
            planetArray[i].setxCoordinate(recLayoutX + width * X);
            planetArray[i].setyCoordinate(recLayoutY + height * Y);
        }
}


    // Getters
    public static PlanetGenerator getPlanetGenerator() {
        return planetGenerator;
    }

    public static Planet[] getPlanetArray() {
        return planetArray;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Good[] getGood() {
        return good;
    }

    public static int getPlanetClicked() {
        return planetClicked;
    }

    public void setHereLabel(int x, int y) {
        hereLabel.setLayoutX(x);
        hereLabel.setLayoutY(y);
    }

    public void planetDetailBtnPressed(ActionEvent actionEvent) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void marketBtnPressed(ActionEvent actionEvent) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("Market.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void shipInventoryBtnPressed(ActionEvent actionEvent) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("ShipInventory.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    private String encounterCheck() {
        Random random = new Random();
        String difficulty = ConfigController.getDifficulty();
        int difficultyOffset;
        String result = "Nobody";
        
        if ("Literally Impossible".equals(difficulty)) {
            difficultyOffset = 4;
        } else if ("Hard".equals(difficulty)) {
            difficultyOffset = 3;
        } else if ("Medium".equals(difficulty)) {
            difficultyOffset = 2;
        } else {
            difficultyOffset = 1;
        }
        
        
        if (random.nextBoolean()) {// 50% chance player encounters nobody
            int[] letsSeeWhoWeMeet = new int[3];
            letsSeeWhoWeMeet[0] = random.nextInt(3) * difficultyOffset; // [0] bandit
            letsSeeWhoWeMeet[1] = random.nextInt(3) * difficultyOffset; // [1] police
            letsSeeWhoWeMeet[2] = random.nextInt(8); // [2] trader
            
            if (letsSeeWhoWeMeet[2] < letsSeeWhoWeMeet[0]) {
                result = "Bandit";
            } else if (letsSeeWhoWeMeet[2] < letsSeeWhoWeMeet[1]) {
                result = "Police";
            } else {
                result = "Trader";
            }
            
        }
        return result;
        
    }
    
    private void travelToAnotherPlanet(MouseEvent event) throws IOException {
        Player.getShip().setFuelCapacity(Player.getShip().getFuelCapacity() - (planetGenerator.getDistanceArray()[planetClicked] / 10));
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }


    public void explore1BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 1;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore2BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 2;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }

    }

    public void explore3BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 3;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore4BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 4;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore5BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 5;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore6BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 6;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore7BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 7;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore8BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 8;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore9BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 9;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }

    public void explore0BtnPressed(MouseEvent event) throws IOException {
        planetClicked = 0;
        planetGenerator.setPlanetArray(planetArray);
        String encounterRole = encounterCheck();
        System.out.println(encounterRole);

        if (Player.getShip().getFuelCapacity() < (planetGenerator.getDistanceArray()[planetClicked] / 10)) {
            errorMessage.setText("You don't have enough fuel left. Please refill.");
        } else {
            travelToAnotherPlanet(event);
        }
    }
}
