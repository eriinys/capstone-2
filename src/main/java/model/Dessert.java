package model;

public class Dessert extends Product{

    public Dessert(String itemName, int quantity) {
        super(itemName, quantity);
    }

    @Override
    public double getPrice(){
        return getBasePrice();
    }

    @Override
    public String getSummary() {
        return String.format("""
                =============🍨Dessert🍨=============
                 -Dessert Type:
                   -%s
                 -Quantity:
                   -%d
                 -Dessert Total:   $%.2f
                """, getItemName(), getQuantity(), getPrice());
    }

}
