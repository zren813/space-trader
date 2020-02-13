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
    private Spinner<Integer> skill1Point;
    @FXML
    private Spinner<Integer> skill2Point;
    @FXML
    private Spinner<Integer> skill3Point;
    @FXML
    private Spinner<Integer> skill4Point;
    @FXML
    private Text errorMessage;

    private static String name;
    private static String difficulty;
    private static int availableSkill;
    private static int numSkill1;
    private static int numSkill2;
    private static int numSkill3;
    private static int numSkill4;


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
        int points = skill1Point.getValue() + skill2Point.getValue()
                + skill3Point.getValue() + skill4Point.getValue();
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
            numSkill1 = skill1Point.getValue();
            numSkill2 = skill2Point.getValue();
            numSkill3 = skill3Point.getValue();
            numSkill4 = skill4Point.getValue();

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

    /**
     * This method is the getter of name
     *
     * @return String name
     */
    public static String getName() {
        return name;
    }

    /**
     * This method is the getter of difficulty
     *
     * @return int difficulty
     */
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * This method is the getter of availableSkill
     *
     * @return int availableSkill
     */
    public static int getAvailableSkill() {
        return availableSkill;
    }

    /**
     * This method is the getter of skill 1 points
     *
     * @return int skill point
     */
    public static int getNumSkill1() {
        return numSkill1;
    }

    /**
     * This method is the getter of skill 2 points
     *
     * @return int skill point
     */
    public static int getNumSkill2() {
        return numSkill2;
    }

    /**
     * This method is the getter of skill 3 points
     *
     * @return int skill point
     */
    public static int getNumSkill3() {
        return numSkill3;
    }

    /**
     * This method is the getter of skill 4 points
     *
     * @return int skill point
     */
    public static int getNumSkill4() {
        return numSkill4;
    }
}
