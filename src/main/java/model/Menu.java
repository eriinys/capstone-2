package model;

public class Menu {
    //contains read-only data that UI prints (name / price)
    //Garlic Bread
    private static final String gb = "Classic Garlic Bread";
    private static final double gbPrice = 4.50;

    //Regular Topping
    private static final String rt1 = "Garlic Mushroom";
    private static final double rt1Price = 1.50;
    private static final String rt2 = "Caramelized Garlic";
    private static final double rt2Price = 1.00;
    private static final String rt3 = "Roasted Garlic";
    private static final double rt3Price = 1.00;
    private static final String rt4 = "Pickled Garlic";
    private static final double rt4Price = 1.00;

    //Premium Topping
    private static final String pt1 = "Roasted Black Garlic";
    private static final double pt1Price = 2.00;
    private static final String pt2 = "Smoked Garlic Cheese";
    private static final double pt2Price = 2.50;
    private static final String pt3 = "Smoked Black Garlic Caviar";
    private static final double pt3Price = 4.25;
    private static final String pt4 = "Truffle-Garlic Butter Lobster";
    private static final double pt4Price = 12.99;

    //Drinks
    private static final String d1 = "Garlic Shot (immunity booster)";
    private static final double d1Price = 4.00;
    private static final String d2 = "Frozen Black Garlic Lemonade";
    private static final double d2Price = 8.25;
    private static final String d3 = "Garlic Bloody Mary";
    private static final double d3Price = 11.50;

    //Sides
    private static final String s1 = "Garlic Soup";
    private static final double s1Price = 6.25;
    private static final String s2 = "Garlic Salad";
    private static final double s2Price = 8.25;
    private static final String s3 = "Garlic Fries";
    private static final double s3Price = 7.25;
    private static final String s4 = "Garlic Naan";
    private static final double s4Price = 5.75;

    //Dessert
    private static final String ds1 = "Garlic Honey Ice Cream";
    private static final double ds1Price = 7.25;
    private static final String ds2 = "Garlic Rice Pudding";
    private static final double ds2Price = 7.25;

    public void printRegularTopping(){
        System.out.printf("""
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                """, rt1, rt1Price, rt2, rt2Price, rt3, rt3Price, rt4, rt4Price);
    }

    public void printPremiumTopping(){
        System.out.printf("""
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                """, pt1, pt1Price, pt2, pt2Price, pt3, pt3Price, pt4, pt4Price);
    }

    public void printDrinks(){
        System.out.printf("""
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                """, d1, d1Price, d2, d2Price, d3, d3Price);
    }

    public void printSides(){
        System.out.printf("""
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                o %s      $%.2f
                """, s1, s1Price, s2, s2Price, s3, s3Price, s4, s4Price);
    }

    public void printDessert(){
        System.out.printf("""
                o %s      $%.2f
                o %s      $%.2f
                """, ds1, ds1Price, ds2, ds2Price);
    }


}
