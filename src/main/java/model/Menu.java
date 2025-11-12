package model;
import java.util.*;

public class Menu {
    //setup static HashMap in the field that belongs specifically to the Menu class, NOT part of individual instance
    //data is shared across every Menu object that is used for validating user input when choosing item from menu option

    //region field
    //Garlic Bread
    private static final String classicGb = "Classic Garlic Bread";
    private static final double classicGbPrice = 4.50;

    //Regular Topping
    private static final Map<String, String> regularTopping = new LinkedHashMap<>();
    private static final Map<String, Double> regularPrices = new HashMap<>();

    //Premium Topping
    private static final Map<String, String> premiumTopping = new LinkedHashMap<>();
    private static final Map<String, Double> premiumPrices = new HashMap<>();

    //Drinks
    private static final Map<String, String> drinkName = new LinkedHashMap<>();
    private static final Map<String, Double> drinkPrices = new LinkedHashMap<>();

    //Sides
    private static final Map<String, String> sideName = new LinkedHashMap<>();
    private static final Map<String, Double> sidePrices = new LinkedHashMap<>();

    //Dessert
    private static final Map<String, String> dessertName = new LinkedHashMap<>();
    private static final Map<String, Double> dessertPrices = new LinkedHashMap<>();
    //endregion

    //region key/value
    static { //used to initialize static field (HashMap) assigning key/value

        //Regular Topping
        regularTopping.put("RT1", "Garlic Mushroom");
        regularPrices.put("RT1", 1.50);
        regularTopping.put("RT2", "Caramelized Garlic");
        regularPrices.put("RT2", 1.00);
        regularTopping.put("RT3", "Roasted Garlic");
        regularPrices.put("RT3", 1.00);
        regularTopping.put("RT4", "Pickled Garlic");
        regularPrices.put("RT4", 1.00);

        //Premium Topping
        premiumTopping.put("PT1", "Roasted Black Garlic");
        premiumPrices.put("PT1", 2.00);
        premiumTopping.put("PT2", "Smoked Garlic Cheese");
        premiumPrices.put("PT2", 2.50);
        premiumTopping.put("PT3", "Smoked Black Garlic Caviar");
        premiumPrices.put("PT3", 4.25);
        premiumTopping.put("PT4", "Truffle-Garlic Butter Lobster");
        premiumPrices.put("PT4", 12.99);

        //Drinks
        drinkName.put("DR1", "Garlic Shot (immunity booster)");
        drinkPrices.put("DR1", 4.00);
        drinkName.put("DR2", "Frozen Black Garlic Lemonade");
        drinkPrices.put("DR2", 8.25);
        drinkName.put("DR3", "Garlic Bloody Mary");
        drinkPrices.put("DR3", 11.50);

        //Sides
        sideName.put("SE1", "Garlic Soup");
        sidePrices.put("SE1", 6.25);
        sideName.put("SE2", "Garlic Salad");
        sidePrices.put("SE2", 8.25);
        sideName.put("SE3", "Garlic Fries");
        sidePrices.put("SE3", 7.25);
        sideName.put("SE4", "Garlic Naan");
        sidePrices.put("SE4", 5.75);

        //Dessert
        dessertName.put("DS1", "Garlic Honey Ice Cream");
        dessertPrices.put("DS1", 7.25);
        dessertName.put("DS2", "Garlic Rice Pudding");
        dessertPrices.put("DS2", 7.25);
    }
    //endregion

    //region getter method
    public static Map<String, String> getRegularTopping() {
        return Collections.unmodifiableMap(regularTopping);
    }
    public static Map<String, Double> getRegularPrices() {
        return Collections.unmodifiableMap(regularPrices);
    }

    public static Map<String, String> getPremiumTopping() {
        return Collections.unmodifiableMap(premiumTopping);
    }
    public static Map<String, Double> getPremiumPrices() {
        return Collections.unmodifiableMap(premiumPrices);
    }

    public static Map<String, String> getDrinkName() {
        return Collections.unmodifiableMap(drinkName);
    }
    public static Map<String, Double> getDrinkPrices() {
        return Collections.unmodifiableMap(drinkPrices);
    }

    public static Map<String, String> getSideName() {
        return Collections.unmodifiableMap(sideName);
    }
    public static Map<String, Double> getSidePrices() {
        return Collections.unmodifiableMap(sidePrices);
    }

    public static Map<String, String> getDessertName() {
        return Collections.unmodifiableMap(dessertName);
    }
    public static Map<String, Double> getDessertPrices() {
        return Collections.unmodifiableMap(dessertPrices);
    }
    //endregion

    //setting custom printer method for displaying each key/value (similar to toString)
    public void printMenu(Map<String, String> names, Map<String, Double> prices){
        //sets condition for printing out name and price equally in every line
        int keyLength = 0;
        int nameLength = 0;
        for (String key : names.keySet()) {
            keyLength = Math.max(keyLength, key.length()); //compares previous length to current key length and returns whichever number is bigger
            nameLength = Math.max(nameLength, names.get(key).length());
        }

        nameLength += 2; //adds extra space

        //formatting outside of loop
        String format = "[%-" + keyLength + "s] %-" + nameLength + "s $%.2f%n";

        //print
        for (String key : names.keySet()) { //keySet() returns collection of keys stored in HashMap
            String value = names.get(key); //looks up the value related to the key
            double price = prices.getOrDefault(key, 0.00); //if it can't find key, returns given default value
            System.out.printf(format, key, value, price); //formatting added after calculation for final print
        }
    }

}
