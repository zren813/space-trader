public class Ship {
    private String name;
    private int cargoCapacity;
    private Good[] itemInventory;
    /**
     * fuel capacity between 0 - 100
     */
    private int fuelCapacity;
    /**
     * health between 0 - 100
     */
    private int health;

    public Ship(String name, int cargoCapacity, Good[] itemInventory, int fuelCapacity, int health) {
        this.name = name;
        this.cargoCapacity = cargoCapacity;
        this.itemInventory = itemInventory;
        this.fuelCapacity = fuelCapacity;
        this.health = health;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public void setItemInventory(Good[] itemInventory) {
        this.itemInventory = itemInventory;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public Good[] getItemInventory() {
        return itemInventory;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getHealth() {
        return health;
    }
}