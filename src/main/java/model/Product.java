package model;

public abstract class Product {

    protected String itemName;
    protected int quantity;
    protected double basePrice;
    protected boolean btc = false;

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

    //is called in Order class to set
    public void setConvertToBtc(boolean isBtc){
        btc = isBtc;
    }
    //is called in subclasses of Product to check and print summary accordingly
    public boolean convertToBtc(){
        return btc;
    }

    public abstract double getPrice();
    public abstract String getSummary();

}
