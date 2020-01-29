import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CharacterController {
    @FXML private Text name;
    @FXML private Text difficulty;
    @FXML private Text skill1point;
    @FXML private Text skill2point;
    @FXML private Text skill3point;
    @FXML private Text skill4point;

    /**
     * This method is to initialize all numbers
     */
    @FXML
    public void initialize() {
        name.setText(ConfigController.getName());
        difficulty.setText(Integer.toString(ConfigController.getDifficulty()));
        skill1point.setText(Integer.toString(ConfigController.getNumSkill1()));
        skill2point.setText(Integer.toString(ConfigController.getNumSkill2()));
        skill3point.setText(Integer.toString(ConfigController.getNumSkill3()));
        skill4point.setText(Integer.toString(ConfigController.getNumSkill4()));
    }

}
