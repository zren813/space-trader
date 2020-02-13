import java.util.Random;
public class Player {
    private String name = ConfigController.getName();
    private Planet currentPlanet;
    private static WorldGenerator worldGenerator = MapController.getWorldGenerator();
    private Planet[] planetArray = worldGenerator.getPlanetArray();
    public Player() {
        Random random = new Random();
        currentPlanet = planetArray[random.nextInt(10)];
    }
    public int[] getDistanceArray(){
        int[] distanceArray = new int[10];
        for (int i = 0; i < 10; i++) {
            distanceArray[i] = (int) Math.sqrt(Math.pow(planetArray[i].getXCoordinate() -
                    currentPlanet.getXCoordinate(),2) + Math.pow(planetArray[i].getYCoordinate()
                    - currentPlanet.getYCoordinate(),2));
        }
        return distanceArray;
    }
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }
}
