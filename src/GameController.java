import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.shape.Circle;

public class GameController {
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

    private Circle[] circleArray = new Circle[10];
    private Planet[] planetArray = new Planet[10];

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
            circleArray[i].setCenterX(planetArray[i].getxCoordinate());
            circleArray[i].setCenterY(planetArray[i].getYCoordinate());
        }
    }
}
