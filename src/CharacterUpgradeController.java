import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ECMAException;

public class CharacterUpgradeController {
    @FXML
    private Text item1NameText;
    @FXML
    private Text item2NameText;
    @FXML
    private Text item3NameText;
    @FXML
    private Text item4NameText;

    @FXML
    private Text item1PriceText;
    @FXML
    private Text item2PriceText;
    @FXML
    private Text item3PriceText;
    @FXML
    private Text item4PriceText;
    @FXML
    private Text item1AbilityText;
    @FXML
    private Text item2AbilityText;
    @FXML
    private Text item3AbilityText;
    @FXML
    private Text item4AbilityText;
    @FXML
    private CheckBox item1CheckBox;
    @FXML
    private CheckBox item2CheckBox;
    @FXML
    private CheckBox item3CheckBox;
    @FXML
    private CheckBox item4CheckBox;
    @FXML
    private CheckBox item1EquippedCheckBox;
    @FXML
    private CheckBox item2EquippedCheckBox;
    @FXML
    private CheckBox item3EquippedCheckBox;
    @FXML
    private CheckBox item4EquippedCheckBox;
    @FXML
    private Text playerAndShipNameText;
    @FXML
    private Text balanceText;
    @FXML
    private Text pilotSkillText;
    @FXML
    private Text fighterSkillText;
    @FXML
    private Text merchantSkillText;
    @FXML
    private Text engineerSkillText;
    @FXML
    private Text errorMessage;
    private Text itemNameText[];
    private Text itemPriceText[];
    private Text itemAbilityText[];
    private CheckBox itemCheckBox[];
    private CheckBox itemEquippedCheckBox[];

    private int itemNumber = 4;
    private Player player;
    private Equipment playerEquipment[];
    private int equipmentNumber;
    private Equipment equipment[];
    public void initialize() {
        player = MapController.getPlayer();
        EquipmentGenerater equipmentGenerater= new EquipmentGenerater();
        equipmentNumber = equipmentGenerater.getEquipmentNumber();
        equipment = equipmentGenerater.getEquipment();
        playerEquipment = player.getEquipment();

        itemNameText = new Text[] {item1NameText, item2NameText, item3NameText, item4NameText};
        itemPriceText= new Text[] {item1PriceText, item2PriceText, item3PriceText, item4PriceText};
        itemAbilityText = new Text[] {item1AbilityText, item2AbilityText, item3AbilityText, item4AbilityText};
        itemCheckBox= new CheckBox[] {item1CheckBox, item2CheckBox, item3CheckBox, item4CheckBox};
        itemEquippedCheckBox = new CheckBox[]{item1EquippedCheckBox, item2EquippedCheckBox, item3EquippedCheckBox, item4EquippedCheckBox};
        // initialize UI info
        for (int i = 0; i < itemNumber; i++) {
            itemNameText[i].setText(equipment[i].getName());
            itemPriceText[i].setText(String.valueOf(equipment[i].getPrice()));
            itemAbilityText[i].setText(equipment[i].getDescription());
        }

        resetSkillCheckBox();
        updateCharacterInfo();
    }

//     reset checkboxes, if the equipment is bought, the box will be set as checked
    public void resetSkillCheckBox() {
        for (int i = 0; i < equipmentNumber; i++) {
            if(playerEquipment[i]!=null){
                itemCheckBox[i].setSelected(true);
            }else{
                itemCheckBox[i].setSelected(false);
            }
        }
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
        Boolean isItemChecked []= new Boolean[equipmentNumber];
        for (int i = 0; i < isItemChecked.length; i++) {
            isItemChecked[i] = itemCheckBox[i].isSelected();
        }
        int totalCost = 0;
        for (int i = 0; i < equipmentNumber; i++) {
            if(isItemChecked[i]==true && playerEquipment[i]== null){
                totalCost += equipment[i].getPrice();
            }
        }

        //check if the player has enough balance
        if(totalCost <= player.getBalance()){
            //update player's balance and skills and add the equipment to player
            player.setBalance(player.getBalance()-totalCost);
            for (int i = 0; i < equipmentNumber; i++) {
                if(isItemChecked[i]==true && playerEquipment[i]== null){
                    player.setPilotSkill(player.getPilotSkill()+equipment[i].getPilotAbilityIncrement());
                    player.setFighterSkill(player.getFighterSkill()+equipment[i].getFighterAbilityIncrement());
                    player.setMerchantSkill(player.getMerchantSkill()+equipment[i].getMerchantAbilityIncrement());
                    player.setEngineerSkill(player.getEngineerSkill()+equipment[i].getEngineerAbilityIncrement());
                    playerEquipment[i] = equipment[i];
                    itemEquippedCheckBox[i].setSelected(true);
                }
            }
        }else{
            errorMessage.setText("You don't have enough balance");
        }

        resetSkillCheckBox();
        updateCharacterInfo();
    }

    public void cancelBtnPressed(ActionEvent actionEvent) {
        resetSkillCheckBox();
    }
}
