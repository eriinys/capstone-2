package model;

public abstract class Product {

    protected String itemName;
    protected int quantity;
    protected double price;

    public Product(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;

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

    public abstract double getPrice();
    public abstract String getSummary();

}
