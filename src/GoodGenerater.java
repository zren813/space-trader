public class GoodGenerater {
    private static int numberOfGood = 15;
    private static Good good[] = new Good[numberOfGood];
    private static String goodName[] = {"Purified Water", "Oil", "Natural Gas", "Microwave", "Bicycle", "Fridge",
            "Solar Cell", "Computer", "Internet", "Cloning Tech", "Quantum Comp", "Space Shuttle",
            "Nuclear Reactor", "Time Travel Kit", "AI"};
    private static int price[] = {10, 20, 30, 50, 80, 100, 300, 800, 800, 1000, 2000, 5000, 10000, 20000, 20000};
    private static int capacity[] = {1, 2, 2, 5, 5, 10, 20, 20, 20, 50, 80, 100, 150, 100, 200};
    private static int techLevel[] = {1, 1, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9, 10, 10};

    public GoodGenerater() {
        for (int i = 0; i < numberOfGood; i++) {
            good[i] = new Good(goodName[i], price[i], capacity[i], techLevel[i]);
        }
    }

    public static Good[] getGood() {
        return good;
    }

    public static int getNumberOfGood() {
        return numberOfGood;
    }

}
