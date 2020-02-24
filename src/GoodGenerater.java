public class GoodGenerater {
    private static int numberOfGood = 5;
    private static Good good[] = new Good[numberOfGood];
    private static String goodName[] = {"Purified Water", "Oil", "Energy Cell", "Electronics", "Antibiotics"};
    private static int price[] = {10,20,30,40,50};
    private static int capacity[] = {1,2,3,4,5};
    public GoodGenerater() {
        for (int i = 0; i < numberOfGood; i++) {
            good[i] = new Good(goodName[i], price[i], capacity[i]);
        }
    }

    public static Good[] getGood() {
        return good;
    }

    public static int getNumberOfGood() {
        return numberOfGood;
    }
}