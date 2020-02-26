import java.util.ArrayList;

public class Player {
    private Planet currentPlanet;
    private static WorldGenerator worldGenerator;
    private static Planet[] planetArray;
    // in order to buy items from market, player needs to have a ship and balance
    private Ship ship;
    private int balance;
    private String name;
    private Equipment equipment[];
    // charachter's skills
    private int pilotSkill;
    private int fighterSkill;
    private int merchantSkill;
    private int engineerSkill;


    public Player() {
        worldGenerator = MapController.getWorldGenerator();
        planetArray = worldGenerator.getPlanetArray();
        currentPlanet = planetArray[0];
        currentPlanet.setVisited(true);
        setCurrentPlanet(planetArray[0]);
        //initialize balance and a ship
        this.balance = 1000;
        ship = new Ship("Apollo 11", 50, 100, 100);
        this.name = ConfigController.getName();
        //initialize skills
        pilotSkill = ConfigController.getPilotSkill();
        fighterSkill = ConfigController.getFighterSkill();
        merchantSkill = ConfigController.getMerchantSkill();
        engineerSkill = ConfigController.getEngineerSkill();
    }

    public int[] getDistanceArray() {
        int[] distanceArray = new int[10];
        for (int i = 0; i < 10; i++) {
            distanceArray[i] = (int) Math.sqrt(Math.pow(planetArray[i].getXCoordinate()
                - currentPlanet.getXCoordinate(), 2) + Math.pow(planetArray[i].getYCoordinate()
                - currentPlanet.getYCoordinate(), 2));
        }
        return distanceArray;
    }

    // Getters and setters
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPilotSkill() {
        return pilotSkill;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    public int getFighterSkill() {
        return fighterSkill;
    }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public int getMerchantSkill() {
        return merchantSkill;
    }

    public void setMerchantSkill(int merchantSkill) {
        this.merchantSkill = merchantSkill;
    }

    public int getEngineerSkill() {
        return engineerSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    public int[] getSkill() {
        int skill[] = {this.getPilotSkill(), this.getFighterSkill(), this.getMerchantSkill(), this.getEngineerSkill()};
        return skill;
    }

    public void setSkill(int skill[]) {
        this.setPilotSkill(skill[0]);
        this.setFighterSkill(skill[1]);
        this.setMerchantSkill(skill[2]);
        this.setEngineerSkill(skill[3]);
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }
}
