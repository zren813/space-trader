import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class EncounterBanditController {
    @FXML
    public Text intro;
    @FXML
    public Button payBanditBtn;

    @FXML
    public void initialize() {
        intro.setText("something");

    }
}
