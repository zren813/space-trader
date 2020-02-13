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
    private Paint[] paintArray = {Color.BLUE, Color.CYAN, Color.YELLOW, Color.PURPLE, Color.PINK, Color.AQUA,
        Color.WHEAT, Color.RED, Color.HONEYDEW, Color.GREEN, Color.DARKGREEN};

    public Planet() {
        Random random = new Random();
        name = NameGenerator.getName();
        technologyLevel = random.nextInt(10);
        xCoordinate = random.nextInt(600) + 200;
        yCoordinate = random.nextInt(400) + 100;
        visited = false;
        discription = "";
        paint = paintArray[random.nextInt(paintArray.length)];
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
            return ("Planet Name: " + getName() + "\nTechnology Level: " + getTechnologyLevel()
                    + "\nDiscription: " + getDescription());
        } else {
            return "UNKNOWN (Please visit first)";
        }
    }
}
