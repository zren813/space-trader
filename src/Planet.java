import java.util.Random;

public class Planet {
    private String name;
    private int technologyLevel;
    private String discription;
    private int xCoordinate;
    private int yCoordinate;
    private boolean visited;

    public Planet() {
        Random random = new Random();
        name = NameGenerator.getName();
        technologyLevel = random.nextInt(10);
        xCoordinate = random.nextInt(600) + 200;
        yCoordinate = random.nextInt(400) + 100;
        visited = false;
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

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }
}
