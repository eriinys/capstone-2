package model;

public abstract class Product {

    protected String itemName;
    protected int quantity;
    protected double basePrice;

    public Product(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean hasSize(){
        return false; //not all products have size; defaults to false
    }

    public String getSize(){
        return ""; //default size method that returns empty string
    }

    public double getSizePrice(){
        return 0;
    }

    public double getBasePrice(){
        return basePrice;
    }

    public void setBasePrice(double bp) {
        basePrice = bp;
    }

    public abstract double getPrice();
    public abstract String getSummary();

}
