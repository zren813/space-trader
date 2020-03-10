import java.util.Random;

public class NpcNameGenerator {
    private static String[] npcNames = {
            "Frank", "Chelsea", "Issac",
            "Selina", "Lucy", "Jerry",
            "Bob", "Matthew", "Jonathan", "Lisa",
            "Robin"};

    public static String getName() {
        Random random = new Random();
        return npcNames[random.nextInt(npcNames.length)];
    }
}
