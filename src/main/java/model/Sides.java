package model;

public class Sides extends Product {

    public Sides(String itemName, int quantity) {
        super(itemName, quantity);
    }

    @Override
    public double getPrice(){
        return getBasePrice();
    }

    @Override
    public String getSummary() {
        return String.format("""
                =============🥗Side🥗=============
                 -Side Type:
                   -%s
                 -Quantity:
                   -%d
                 -Side Total:   $%.2f
                """, getItemName(), getQuantity(), getPrice());
    }


}
