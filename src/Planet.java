import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Planet {
    private int planetID;
    private String name;
    private int technologyLevel;
    private int xCoordinate;
    private int yCoordinate;
    private boolean visited;
    private Paint paint;


    @SuppressWarnings("checkstyle:OperatorWrap")
    public Planet(int planetID, String name, int technologyLevel) {
        this.planetID = planetID;
        this.name = name;
        this.technologyLevel = technologyLevel;
        visited = false;
        paint = Color.color(Math.random(), Math.random(), Math.random());
    }


    public String displayInfo() {
        Random random = new Random();
        if (isVisited()) {
            String description = String.format("This planet is called %s. ", name) +
                String.format("The technology level is %d. ", technologyLevel) +
                String.format("There are %d thousands population on the planet",
                    random.nextInt(technologyLevel * 1000));
            return description;
        } else {
            return "UNKNOWN (Please visit first)";
        }
    }

    // Getters
    public int getPlanetID() {
        return planetID;
    }

    public String getName() {
        return name;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public boolean isVisited() {
        return visited;
    }

    public Paint getPaint() {
        return paint;
    }

    // Setters
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
