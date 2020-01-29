import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ConfigController {
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox difficultyLevel;
    @FXML
    private ChoiceBox skill1Point;
    @FXML
    private ChoiceBox skill2Point;
    @FXML
    private ChoiceBox skill3Point;
    @FXML
    private ChoiceBox skill4Point;



    public void configBtnPressed(ActionEvent event) {
        if (!nameTextField.getText().isEmpty() && calculateSkillPoints()) {
            System.out.println("passed");
        }
    }

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

    public boolean calculateSkillPoints() {
        return ((int) skill1Point.getValue() + (int) skill2Point.getValue() + (int) skill3Point.getValue()
                + (int) skill4Point.getValue() <= 6);
    }


}
