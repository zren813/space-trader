import java.util.Random;

public class NameGenerator {
    private static String[] galaxyNames = {"Andromeda", "Corvus", "Centaurus", "Coma Berenices", "Ursa Major",
            "Virgo", "Sculptor", "Ursa Major", "Circinus",  "Sculptor", "Triangulum", "Draco", "Canes Venatici",
            "Hydra", "Virgo", };
    private static String[] romanNumeral = {"I", "II", "III", "IV", "V", "VI", "VII", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};

    public static String getName() {
        Random random = new Random();
        return romanNumeral[random.nextInt(romanNumeral.length)] + galaxyNames[random.nextInt(galaxyNames.length)];
    }
}
