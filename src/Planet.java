import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class Planet {
    private String name;
    private int technologyLevel;
    private String discription;
    private int xCoordinate;
    private int yCoordinate;
    private boolean visited;
    private Paint paint;

    public Planet() {
        Random random = new Random();
        name = NameGenerator.getName();
        technologyLevel = random.nextInt(10) + 1;
        xCoordinate = random.nextInt(700) + 40;
        yCoordinate = random.nextInt(400) + 40;
        visited = false;
        discription = "";
        paint = Color.color(Math.random(), Math.random(), Math.random());
    }

    public String getName() {
        return name;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public String getDescription() {
        return discription;
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
