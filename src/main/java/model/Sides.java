package model;

public class Sides extends Product {
    private double price;

    public Sides(String itemName, int quantity) {
        super(itemName, quantity);
    }

    @Override
    public double getPrice(){
        return price;
    }

    @Override
    public String getSummary() {
        return String.format("%s  x%d    +$%.2f", getItemName(), getQuantity(), getPrice());
    }


}
