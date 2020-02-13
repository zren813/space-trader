import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Planet {
    private String name;
    private int technologyLevel;
    private String description;
    private int xCoordinate;
    private int yCoordinate;
    private boolean visited;
    private Paint paint;

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @SuppressWarnings("checkstyle:OperatorWrap")
    public Planet() {

        Random random = new Random();
        name = NameGenerator.getName();
        technologyLevel = random.nextInt(10) + 1;
        xCoordinate = random.nextInt(300);
        yCoordinate = random.nextInt(300);
        visited = false;
        String[] unit = {"thousand.", "million.", "billion."};
        description = "This planet is called " + name + " and the technology level is " + technologyLevel + ". " +
                "The population on this planet is " + random.nextInt(1000) + " " +
                unit[random.nextInt(3)];
        paint = Color.color(Math.random(), Math.random(), Math.random());
    }

    public String getName() {
        return name;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public String getDescription() {
        return description;
    }

    public int getXCoordinate() {
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

    public Paint getPaint() {
        return paint;
    }

    public String displayInfo() {
        if (isVisited()) {
            return (getName() + "\n" + getTechnologyLevel() + "\n" + getDescription());
        } else {
            return "UNKNOWN (Please visit first)";
        }
    }
}
