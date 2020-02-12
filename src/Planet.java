import java.util.Random;

public class Planet {
    private String name;
    private int technologyLevel;
    private String discription;
    private int xCoordinate;
    private int yCoordinate;

    public Planet() {
        Random random = new Random();
        name = NameGenerator.getName();
        technologyLevel = random.nextInt(10);
        xCoordinate = random.nextInt(600);
        yCoordinate = random.nextInt(400);
    }

    public String getName() {
        return name;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public String getDiscription() {
        return discription;
    }

    public int getYCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }
}
