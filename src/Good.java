public class Good {
    private String name;
    private int quantity;
    private int price;
    private int volume;

    public Good(String name, int price, int volume) {
        this.name = name;
        this.quantity = 0;
        this.price = price;
        this.volume = volume;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
