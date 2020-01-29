import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigController {

    @FXML private TextField nameTextField;
    @FXML private ChoiceBox difficultyLevel;
    @FXML private ChoiceBox skill1Point;
    @FXML private ChoiceBox skill2Point;
    @FXML private ChoiceBox skill3Point;
    @FXML private ChoiceBox skill4Point;

    private static String name;
    private static int difficulty;
    private static int numSkill1;
    private static int numSkill2;
    private static int numSkill3;
    private static int numSkill4;

    /**
     * This method is to initialize all Choiceboxes
     */
    @FXML
    public void initialize() {
        difficultyLevel.setValue(0);
        skill1Point.setValue(0);
        skill2Point.setValue(0);
        skill3Point.setValue(0);
        skill4Point.setValue(0);
        difficultyLevel.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
        skill1Point.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
        skill2Point.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
        skill3Point.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
        skill4Point.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
    }

    /**
     * This method is to add all skill points and check if total is larger than 6
     * @return true if total is less or equal than 6
     */
    public boolean calculateSkillPoints() {
        return ((int) skill1Point.getValue() + (int) skill2Point.getValue()
                + (int) skill3Point.getValue() + (int) skill4Point.getValue() <= 6);
    }

    /**
     * This method is to support the continue button
     * @param event fired when the button is pressed
     * @throws IOException throw IOException
     */
    public void configBtnPressed(ActionEvent event) throws IOException {
        if (!nameTextField.getText().isEmpty() && calculateSkillPoints()) {
            name = nameTextField.getText();
            numSkill1 = (int) skill1Point.getValue();
            numSkill2 = (int) skill2Point.getValue();
            numSkill3 = (int) skill3Point.getValue();
            numSkill4 = (int) skill4Point.getValue();

            Parent configParent = FXMLLoader.load(getClass().getResource("Character.fxml"));
            Scene configScene = new Scene(configParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(configScene);
            window.show();
        }
    }

    /**
     * This method is the getter of name
     * @return String name
     */
    public static String getName() {
        return name;
    }

    /**
     * This method is the getter of difficulty
     * @return int difficulty
     */
    public static int getDifficulty() {
        return difficulty;
    }

    /**
     * This method is the getter of skill 1 points
     * @return int skill point
     */
    public static int getNumSkill1() {
        return numSkill1;
    }

    /**
     * This method is the getter of skill 2 points
     * @return int skill point
     */
    public static int getNumSkill2() {
        return numSkill2;
    }

    /**
     * This method is the getter of skill 3 points
     * @return int skill point
     */
    public static int getNumSkill3() {
        return numSkill3;
    }

    /**
     * This method is the getter of skill 4 points
     * @return int skill point
     */
    public static int getNumSkill4() {
        return numSkill4;
    }
}
