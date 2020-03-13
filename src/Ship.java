public class Ship {
    private String name;
    private int cargoCapacity;
    private Good[] shipInventory;
    /**
     * fuel capacity between 0 - 100
     */
    private int fuelCapacity;
    /**
     * health between 0 - 100
     */
    private int health;

    public Ship(String name, int cargoCapacity, int fuelCapacity, int health) {
        this.name = name;
        this.cargoCapacity = cargoCapacity;
        this.shipInventory = null;
        this.fuelCapacity = fuelCapacity;
        this.health = health;
    }
    public boolean checkInventoryEmpty() {
        int sumOfItems = 0;
        for (Good good: shipInventory) {
            sumOfItems = sumOfItems + good.getQuantity();
        }
        return sumOfItems == 0;
    }
    public void setInventoryEmpty() {
        for (Good good: shipInventory) {
            good.setQuantity(0);
        }
    }
    //assume the capacity requirement is met
    public void addItems(int indexOfProduct, int numberToAdd) {
        shipInventory[indexOfProduct].setQuantity(shipInventory[indexOfProduct].getQuantity() + numberToAdd);
    }
    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public Good[] getItemInventory() {
        return shipInventory;
    }

    public void setItemInventory(Good[] itemInventory) {
        this.shipInventory = itemInventory;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}