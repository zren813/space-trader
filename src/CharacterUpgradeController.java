import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ECMAException;

public class CharacterUpgradeController {
    @FXML
    public Text pilotPriceText;
    @FXML
    public Text fighterPriceText;
    @FXML
    public Text merchantPriceText;
    @FXML
    public Text engineerPriceText;
    @FXML
    public Spinner<Integer> pilotSpinner;
    @FXML
    public Spinner<Integer> fighterSpinner;
    @FXML
    public Spinner<Integer> merchantSpinner;
    @FXML
    public Spinner<Integer> engineerSpinner;
    @FXML
    public Text playerAndShipNameText;
    @FXML
    public Text balanceText;
    @FXML
    public Text pilotSkillText;
    @FXML
    public Text fighterSkillText;
    @FXML
    public Text merchantSkillText;
    @FXML
    public Text engineerSkillText;
    @FXML
    public Text errorMessage;

    int skillPrice[] = {40, 50, 30, 100};
    private Player player;

    public void initialize() {
        player = MapController.getPlayer();

        // initialize UI info
        pilotPriceText.setText(String.valueOf(skillPrice[0]));
        fighterPriceText.setText(String.valueOf(skillPrice[1]));
        merchantPriceText.setText(String.valueOf(skillPrice[2]));
        engineerPriceText.setText(String.valueOf(skillPrice[3]));
        resetSkillSpinner();
        updateCharacterInfo();
    }

    // reset skill spinner with min and max=10
    public void resetSkillSpinner() {
        pilotSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(player.getPilotSkill(), 10, player.getPilotSkill()));
        fighterSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(player.getFighterSkill(), 10, player.getFighterSkill()));
        merchantSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(player.getMerchantSkill(), 10, player.getMerchantSkill()));
        engineerSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(player.getEngineerSkill(), 10, player.getEngineerSkill()));
    }

    public void updateCharacterInfo() {
        playerAndShipNameText.setText(String.format("%s(%s)", player.getName(), player.getShip().getName()));
        balanceText.setText(String.format("Balance: %d", player.getBalance()));
        pilotSkillText.setText(String.valueOf("Pilot: "+player.getPilotSkill()));
        fighterSkillText.setText(String.valueOf("Fighter: "+player.getFighterSkill()));
        merchantSkillText.setText(String.valueOf("Merchant: "+player.getMerchantSkill()));
        engineerSkillText.setText(String.valueOf("Engineer: "+player.getEngineerSkill()));

    }

    public void marketBtnPressed(ActionEvent actionEvent) throws Exception{
        Parent configParent = FXMLLoader.load(getClass().getResource("Market.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void exitPressed(ActionEvent actionEvent)throws Exception {
        Parent configParent = FXMLLoader.load(getClass().getResource("PlanetView.fxml"));
        Scene configScene = new Scene(configParent);
        configScene.getStylesheets().add("app.css");
        configScene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(configScene);
        window.show();
    }

    public void confirmBtnPressed(ActionEvent actionEvent) {
        //level to upgrade = chosen skill level - player level
        int level[] = {pilotSpinner.getValue(), fighterSpinner.getValue(), merchantSpinner.getValue(), engineerSpinner.getValue()};
        int playerSkill[] = {player.getPilotSkill(), player.getFighterSkill(), player.getMerchantSkill(), player.getEngineerSkill()};
        int totalCost = 0;
        for (int i = 0; i < level.length; i++) {
            totalCost += skillPrice[i] * (level[i] - playerSkill[i]);
        }
        //check if the player has enough balance
        if(totalCost <= player.getBalance()){
            player.setBalance(player.getBalance()-totalCost);
            player.setPilotSkill(level[0]);
            player.setFighterSkill(level[1]);
            player.setMerchantSkill(level[2]);
            player.setEngineerSkill(level[3]);
        }else{
            errorMessage.setText("You don't have enough balance");

        }
        resetSkillSpinner();
        updateCharacterInfo();
    }

    public void cancelBtnPressed(ActionEvent actionEvent) {
        resetSkillSpinner();
    }
}
