package model;

import java.util.*;

public class GarlicBread extends Product{
    private String breadType;
    private String size;
    private boolean specialized;
    private double basePrice;
    private List<Topping> toppings;

    public GarlicBread(String itemName, String size, int quantity, String breadType, boolean specialized, List<Topping> toppings) {
        super(itemName, quantity);
        this.breadType = breadType;
        this.size = size;
        this.specialized = specialized;
        this.toppings = new ArrayList<>(toppings);
        this.basePrice = 4.50;
    }

    public String getBreadType() {
        return breadType;
    }

    @Override
    public String getSize(){
        return size; //overrides default getSize method's empty string
    }

    public boolean isSpecialized() {
        return specialized;
    }

    public double getSizePrice(){
        double sizePrice = 0.00;
        if (size.equalsIgnoreCase("s")){
            sizePrice = 0.00;
        } else if (size.equalsIgnoreCase("m")){
            sizePrice = 2.00;
        } else if (size.equalsIgnoreCase("l")){
            sizePrice = 3.25;
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
    public boolean hasSize(){
        return true; //override hasSize to true for product with size
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
                 -Size: 
                   -%s
                 -Toppings:
                   -%s
                 -Special Option:
                   -%s""", bread, breadSize, top, isSpecialized);
    }
}
