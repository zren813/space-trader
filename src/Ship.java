import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public Ship(String name, int cargoCapacity, int fuelCapacity, int health) {
        this.name = name;
        this.cargoCapacity = cargoCapacity;
        this.itemInventory = null;
        this.fuelCapacity = fuelCapacity;
        this.health = health;
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
        return itemInventory;
    }

    public void setItemInventory(Good[] itemInventory) {
        this.itemInventory = itemInventory;
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