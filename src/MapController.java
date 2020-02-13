import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.control.Tooltip;

import java.util.Random;

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
    private Label infoLabel;

    private Circle[] circleArray = new Circle[10];
    private Planet[] planetArray = new Planet[10];
    private Tooltip[] toolTipArray = new Tooltip[10];


    @FXML
    public void initialize() {
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
        for (int i = 0; i < 10; i++) {
            planetArray[i] = new Planet();
            toolTipArray[i] = new Tooltip();
            circleArray[i].setCenterX(planetArray[i].getXCoordinate());
            circleArray[i].setCenterY(planetArray[i].getYCoordinate());
            circleArray[i].setFill(planetArray[i].getPaint());
            toolTipArray[i].setText(planetArray[i].displayInfo());
            Tooltip.install(circleArray[i],toolTipArray[i]);
        }
        infoLabel = new Label();
    }
}
