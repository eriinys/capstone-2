package model;

public class Dessert extends Product{

    public Dessert(String itemName, int quantity, double price) {
        super(itemName, quantity, price);
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
