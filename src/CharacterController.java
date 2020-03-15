import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterController {
    @FXML
    private Text nameText;
    @FXML
    private Text difficultyText;
    @FXML
    private Text creditsText;
    @FXML
    private Text pilotText;
    @FXML
    private Text fighterText;
    @FXML
    private Text merchantText;
    @FXML
    private Text engineerText;

    /**
     * This method is to initialize all numbers
     */
    @FXML
    public void initialize() {
        updateUI();
    }

    private void updateUI() {
        nameText.setText(ConfigController.getName());
        difficultyText.setText(ConfigController.getDifficulty());
        creditsText.setText(String.valueOf(ConfigController.getBalance()));
        pilotText.setText(Integer.toString(ConfigController.getPilotSkill()));
        fighterText.setText(Integer.toString(ConfigController.getFighterSkill()));
        merchantText.setText(Integer.toString(ConfigController.getMerchantSkill()));
        engineerText.setText(Integer.toString(ConfigController.getEngineerSkill()));
    }

    public void startBtnPressed(ActionEvent event) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("Map.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
