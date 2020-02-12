import javafx.fxml.FXML;

public class GameController {
    private Planet[] planetArray = new Planet[10];

    @FXML
    public void initialize() {
        for (int i = 0; i < 10; i++) {
            planetArray[i] = new Planet();

        }
    }
}
