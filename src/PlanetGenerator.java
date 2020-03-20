import java.util.Random;

public class PlanetGenerator {
    private static int numberOfPlanet = 10;
    private static Planet[] planetArray = new Planet[numberOfPlanet];
    private static int[] distanceArray = new int[numberOfPlanet];

    private static String[] galaxyNames = {
        "Jupiter", "Corvus", "Mercury", "Saturn", "Virgo",
        "Sculptor", "Uranus", "Draco", "Hydra", "Mars"};


    public PlanetGenerator() {
        for (int i = 0; i < numberOfPlanet; i++) {
            Random random = new Random();
            int technologyLevel = random.nextInt(10) + 1;
            planetArray[i] = new Planet(i, galaxyNames[i], technologyLevel);
        }
    }

    public static void calculateDistanceArray(Planet currentPlanet) {
        int curX = currentPlanet.getXCoordinate();
        int curY = currentPlanet.getYCoordinate();
        for (int i = 0; i < distanceArray.length; i++) {
            int tarX = planetArray[i].getXCoordinate();
            int tarY = planetArray[i].getYCoordinate();
            distanceArray[i] = (int) Math.sqrt(Math.pow(curX - tarX, 2) + Math.pow(curY - tarY, 2));
        }
    }

    // Getters
    public static int[] getDistanceArray(Planet currentPlanet) {
        calculateDistanceArray(currentPlanet);
        return distanceArray;
    }

    public static int[] getDistanceArray() {
        return distanceArray;
    }

    public static Planet[] getPlanetArray() {
        return planetArray;
    }

    // Setters
    public static void setPlanetArray(Planet[] planetArray) {
        PlanetGenerator.planetArray = planetArray;
    }
}
