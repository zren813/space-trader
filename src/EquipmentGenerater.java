public class EquipmentGenerater {
    private int equipmentNumber = 4;
    private Equipment[] equipment = new Equipment[equipmentNumber];
    private String[] equipmentName = {"Red Bull", "sword", "scroll", "wrench"};
    private int[] equipmentPrice = {500, 400, 300, 200};
    private int[][] skillincrement = {{5, 0, 0, 0}, {0, 4, 0, 0}, {0, 0, 3, 0}, {0, 0, 0, 2}};
    private Player player;

    public EquipmentGenerater() {
        for (int i = 0; i < equipmentNumber; i++) {
            equipment[i] = new Equipment(equipmentName[i], equipmentPrice[i], skillincrement[i][0],
                skillincrement[i][1], skillincrement[i][2], skillincrement[i][3]);
        }
        player = MapController.getPlayer();
        player.setEquipment(new Equipment[equipmentNumber]);
        player.setEquipped(new Boolean[]{false, false, false, false, false});

    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public int getEquipmentNumber() {
        return equipmentNumber;
    }
}
