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

public class ConfigViewController {

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
    private Spinner<Integer> skillSpinner[];

    private static String name;
    private static String difficulty;
    private static int pilotSkill;
    private static int fighterSkill;
    private static int merchantSkill;
    private static int engineerSkill;
    private static int balance;
    private static int skillArray[] = new int[4];
    private static Player player;

    /**
     * This method is to initialize all Choiceboxes
     */
    @FXML
    public void initialize() {
        setUpUIObject();
        updateUI();
    }

    public void setUpUIObject() {
        skillSpinner = new Spinner[]{pilotSP, fighterSP, merchantSP, engineerSP};
    }

    private void updateUI() {
        difficultyLevel.setItems(FXCollections.observableArrayList("Easy",
            "Medium", "Hard", "Literally Impossible"));
        difficultyLevel.setValue("Easy");
        availableSkillPoint.setText("Please allocate your skill points (you have 8 points total): ");
        difficultyLevelListener();
    }

    private void difficultyLevelListener() {
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
    }


//    This method is to support the continue button

    public void configBtnPressed(ActionEvent event) throws IOException {
        setUpConfig();

        if (nameTextField.getText().isEmpty()) {
            errorMessage.setText("!Error: empty name!");
        } else if (!checkSkillPoints()) {
            errorMessage.setText("Overall skill points exceed the limit!!!");
            resetSpinner();
        } else {
            if (!nameTextField.getText().isEmpty() && checkSkillPoints()) {
                setUpPlayer();
                goToCharacterView(event);
            }
        }
    }

    // setup all config including name, difficulty, and skills
    public void setUpConfig() {
        name = nameTextField.getText();
        difficulty = difficultyLevel.getValue();
        pilotSkill = pilotSP.getValue();
        fighterSkill = fighterSP.getValue();
        merchantSkill = merchantSP.getValue();
        engineerSkill = engineerSP.getValue();

        if ("Literally Impossible".equals(ConfigViewController.getDifficulty())) {
            balance = 500;
        } else if ("Hard".equals(ConfigViewController.getDifficulty())) {
            balance = 1000;
        } else if ("Medium".equals(ConfigViewController.getDifficulty())) {
            balance = 1500;
        } else {
            balance = 2000;
        }

        skillArray = new int[]{pilotSkill, fighterSkill, merchantSkill, engineerSkill};
    }

    // reset spinners to 0
    public void resetSpinner() {
        for (int i = 0; i < skillSpinner.length; i++) {
            skillSpinner[i].getValueFactory().setValue(0);
        }
    }

    //check if total skill points are valid
    public boolean checkSkillPoints() {
        int totalSkillpoint = 0;
        for (int i = 0; i < skillArray.length; i++) {
            totalSkillpoint += skillArray[i];
        }
        if ("Literally Impossible".equals(difficultyLevel.getValue())) {
            return totalSkillpoint <= 2;
        } else if ("Hard".equals(difficultyLevel.getValue())) {
            return totalSkillpoint <= 4;
        } else if ("Medium".equals(difficultyLevel.getValue())) {
            return totalSkillpoint <= 6;
        } else {
            return totalSkillpoint <= 8;
        }
    }

    private void setUpPlayer() {
        player = new Player(name, balance, skillArray);
    }

    // Go to next view
    public void goToCharacterView(ActionEvent event) throws IOException {
        Parent configParent = FXMLLoader.load(getClass().getResource("CharacterView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }


    //getters
    public static String getDifficulty() {
        return difficulty;
    }

    public static String getName() {
        return name;
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

    public static int getBalance() {
        return balance;
    }

    public static int[] getSkillArray() {
        return skillArray;
    }

    public static Player getPlayer() {
        return player;
    }

}
