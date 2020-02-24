public class Good {
    private String name;
    private double base_price;
    private int number;
    public Good(String name, double base_price) {
        this.name = name;
        this.base_price = base_price;
        this.number = 0;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
