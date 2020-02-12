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

    private Circle[] circleArray = {planet1, planet2, planet3, planet4,
            planet5, planet6, planet7, planet8, planet9, planet10};
    private Planet[] planetArray = new Planet[10];

    @FXML
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            planetArray[i] = new Planet();
            circleArray[i].setCenterX(planetArray[i].getxCoordinate());
            circleArray[i].setCenterY(planetArray[i].getYCoordinate());
        }
    }
}
