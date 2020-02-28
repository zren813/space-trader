import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigController {

    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<String> difficultyLevel;
    @FXML
    private Text availableSkillPoint;
    @FXML
    private Spinner<Integer> pilotSP;
    @FXML
    private Spinner<Integer> fighterSP;
    @FXML
    private Spinner<Integer> merchantSP;
    @FXML
    private Spinner<Integer> engineerSP;
    @FXML
    private Text errorMessage;

    private static String name;
    private static String difficulty;
    private static int pilotSkill;
    private static int fighterSkill;
    private static int merchantSkill;
    private static int engineerSkill;


    /**
     * This method is to initialize all Choiceboxes
     */
    @FXML
    public void initialize() {
        difficultyLevel.getSelectionModel().
            selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue,
                                    Number number, Number number2) {
                    if ("Literally Impossible".equals(difficultyLevel.getItems().get((Integer)
                        number2))) {
                        availableSkillPoint.setText("Please allocate your "
                            + "skill points (you have 2 points total): ");
                    } else if ("Hard".equals(difficultyLevel.getItems().get((Integer)
                        number2))) {
                        availableSkillPoint.setText("Please allocate your "
                            + "skill points (you have 4 points total): ");
                    } else if ("Medium".equals(difficultyLevel.getItems().get((Integer)
                        number2))) {
                        availableSkillPoint.setText("Please allocate your "
                            + "skill points (you have 6 points total): ");
                    } else {
                        availableSkillPoint.setText("Please allocate your "
                            + "skill points (you have 8 points total): ");
                    }
                }
            });
        difficultyLevel.setItems(FXCollections.observableArrayList("Easy",
            "Medium", "Hard", "Literally Impossible"));
        difficultyLevel.setValue("Easy");

    }


    /**
     * This method is to add all skill points and check if total is larger than 6
     *
     * @return true if total is less or equal than 6
     */
    public boolean calculateSkillPoints() {
        int points = pilotSP.getValue() + fighterSP.getValue()
            + merchantSP.getValue() + engineerSP.getValue();
        if ("Literally Impossible".equals(difficultyLevel.getValue())) {
            return points <= 2;
        } else if ("Hard".equals(difficultyLevel.getValue())) {
            return points <= 4;
        } else if ("Medium".equals(difficultyLevel.getValue())) {
            return points <= 6;
        } else {
            return points <= 8;
        }
    }

    /**
     * This method is to support the continue button
     *
     * @param event fired when the button is pressed
     * @throws IOException throw IOException
     */
    public void configBtnPressed(ActionEvent event) throws IOException {
        if (!nameTextField.getText().isEmpty() && calculateSkillPoints()) {
            name = nameTextField.getText();
            difficulty = difficultyLevel.getValue();
            pilotSkill = pilotSP.getValue();
            fighterSkill = fighterSP.getValue();
            merchantSkill = merchantSP.getValue();
            engineerSkill = engineerSP.getValue();

            Parent configParent = FXMLLoader.load(getClass().getResource("Character.fxml"));
            Scene configScene = new Scene(configParent);
            configScene.getStylesheets().add("app.css");
            configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(configScene);
            window.show();
        }
        if (!calculateSkillPoints() && nameTextField.getText().isEmpty()) {
            errorMessage.setText("You need to put a name "
                + "and overall skill points exceed the limit!!!");
        } else if (!calculateSkillPoints()) {
            errorMessage.setText("Overall skill points exceed the limit!!!");
        } else {
            errorMessage.setText("You need to put a name!!!");
        }
    }

    public static String getName() {
        return name;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static int getPilotSkill() {
        return pilotSkill;
    }

    public static int getFighterSkill() {
        return fighterSkill;
    }

    public static int getMerchantSkill() {
        return merchantSkill;
    }

    public static int getEngineerSkill() {
        return engineerSkill;
    }
}
