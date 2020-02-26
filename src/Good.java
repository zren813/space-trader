public class Good {
    private String name;
    private int quantity;
    private int techLevel;
    private int price;
    private int volume;
    private static int basePrice = 10;

    public Good(String name, int techLevel, int volume) {
        this.name = name;
        this.quantity = 0;
        this.techLevel = techLevel;
        this.volume = volume;
        calculatePrice();
    }

    public void calculatePrice() {
        this.price = basePrice * this.techLevel;
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

    public static int getBasePrice() {
        return basePrice;
    }

    public static void setBasePrice(int newBasePrice) {
        basePrice = newBasePrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }
}
