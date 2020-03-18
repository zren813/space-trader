public class Player {
    private static Planet currentPlanet;
    // in order to buy items from market, player needs to have a ship and balance
    private static Ship ship;
    private static int balance;
    private static String name;
    private Equipment[] equipment;
    private Boolean[] equipped;
    // charachter's skills
    private int pilotSkill;
    private int fighterSkill;
    private int merchantSkill;
    private int engineerSkill;
    private int skillArray[] = new int[4];


    public Player(String name, int balance, int skillArray[]) {
        this.name = name;
        this.balance = balance;
        setSkillArray(skillArray);
        ship = new Ship("Apollo 11", 2000, 100, 100);
    }

    // Getters
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public Ship getShip() {
        return ship;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public Boolean[] getEquipped() {
        return equipped;
    }

    public int getPilotSkill() {
        return pilotSkill;
    }

    public int getFighterSkill() {
        return fighterSkill;
    }

    public int getMerchantSkill() {
        return merchantSkill;
    }

    public int getEngineerSkill() {
        return engineerSkill;
    }

    public int[] getSkillArray() {
        return skillArray;
    }

    //Setters
    public static void setCurrentPlanet(Planet inputCurrentPlanet) {
        currentPlanet = inputCurrentPlanet;
    }

    public static void setShip(Ship ship) {
        Player.ship = ship;
    }

    public static void setBalance(int balance) {
        Player.balance = balance;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }

    public void setEquipped(Boolean[] equipped) {
        this.equipped = equipped;
    }

    public void setEquipped(int whichEquip, boolean equipped) {
        this.equipped[whichEquip] = equipped;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
        this.skillArray[0] = pilotSkill;
    }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
        this.skillArray[1] = fighterSkill;
    }

    public void setMerchantSkill(int merchantSkill) {
        this.merchantSkill = merchantSkill;
        this.skillArray[2] = merchantSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
        this.skillArray[3] = engineerSkill;
    }

    public void setSkillArray(int[] skillArray) {
        this.pilotSkill = skillArray[0];
        this.fighterSkill = skillArray[1];
        this.merchantSkill = skillArray[2];
        this.engineerSkill = skillArray[3];
        this.skillArray = skillArray;

    }

}
