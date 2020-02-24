public class Good {
    private String name;
    private int quantity;
    private int techLevel;
    private int price;
    private int capacity;
    private static int basePrice = 10;

    public Good(String name, int techLevel, int capacity) {
        this.name = name;
        this.techLevel = techLevel;
        this.capacity = capacity;
        this.quantity = 0;
        calculatePrice();
    }
    public void calculatePrice(){
        this.price = this.getBasePrice()* this.techLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
        calculatePrice();
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
        calculatePrice();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }
}
