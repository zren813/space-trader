public class Good {
    private String name;
    private int quantity;
    private int basePrice;
    private int volume;

    public Good(String name, int price, int volume) {
        this.name = name;
        this.basePrice = price;
        this.volume = volume;
        quantity = 0;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVolume() {
        return volume;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
