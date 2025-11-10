package model;

import java.util.*;

public class GarlicBread extends Product{
    private String breadType;
    private String size;
    private boolean specialized;
    private double basePrice;
    private List<Topping> toppings;

    public GarlicBread(String itemName, String size, int quantity, double price, String breadType, boolean specialized) {
        super(itemName, quantity, price);
        this.breadType = breadType;
        this.size = size;
        this.specialized = specialized;
        this.toppings = new ArrayList<>();
        this.basePrice = 5.50;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getSize(){
        return size;
    }

    public boolean isSpecialized() {
        return specialized;
    }

    public double getSizePrice(){
        double sizePrice = 0.00;
        if (size.equalsIgnoreCase("s")){
            sizePrice = 0.00;
        } else if (size.equalsIgnoreCase("m")){
            sizePrice = 1.50;
        } else if (size.equalsIgnoreCase("l")){
            sizePrice = 3.00;
        }
        return sizePrice;
    }
    
    public double getSpecialPrice(){
        double additionalCost = 0.00;
        if (specialized){
            additionalCost = 2.50;
        }
        return additionalCost;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        double toppingPrice = 0.00;
        for (Topping topping : toppings){
            toppingPrice += topping.getTotalPrice() * topping.getPortion();
        }
        double total = basePrice + toppingPrice + getSpecialPrice();
        return total;
    }

    @Override
    public String getSummary() {
        String bread = getBreadType();
        String breadSize = String.format("%s         +$%.2f", getSize().toUpperCase(), getSizePrice());
        String top = "";
        for (Topping t : toppings){
            top = String.format("%s  x%d    +$%.2f", t.getName(), t.getPortion(), t.getTotalPrice());
        }
        String isSpecialized;
        if (specialized){
            isSpecialized = String.format("Cheese Stuffed Garlic Bread         +$%.2f", getSpecialPrice());
        } else {
            isSpecialized = "N/A";
        }
        return String.format("""
                Garlic Bread🥖
                 -Bread Type: 
                   -%s
                 -Bread Size: 
                   -%s
                 -Toppings:
                   -%s
                 -Special Option:
                   -%s""", bread, breadSize, top, isSpecialized);
    }
}
