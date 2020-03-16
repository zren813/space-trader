import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterViewController {
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
        nameText.setText(ConfigViewController.getName());
        difficultyText.setText(ConfigViewController.getDifficulty());
        creditsText.setText(String.valueOf(ConfigViewController.getBalance()));
        pilotText.setText(Integer.toString(ConfigViewController.getPilotSkill()));
        fighterText.setText(Integer.toString(ConfigViewController.getFighterSkill()));
        merchantText.setText(Integer.toString(ConfigViewController.getMerchantSkill()));
        engineerText.setText(Integer.toString(ConfigViewController.getEngineerSkill()));
    }

    public void startBtnPressed(ActionEvent event) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("MapView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }
}
