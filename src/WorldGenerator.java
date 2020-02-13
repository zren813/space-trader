public class WorldGenerator {
    private Planet[] planetArray = new Planet[10];

    public WorldGenerator() {
        for (int i = 0; i < 10; i++) {
            planetArray[i] = new Planet();
        }
    }

    public Planet[] getPlanetArray() {
        return planetArray;
    }
}
