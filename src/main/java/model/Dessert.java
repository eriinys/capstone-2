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
        String summary = "";
        if (!convertToBtc()) {
            summary = String.format("""
                    =============🍨Dessert🍨=============
                     -Dessert Type:
                       -%s
                     -Quantity:
                       x%d
                     -Dessert Total:   $%.2f
                    """, getItemName(), getQuantity(), getPrice());
        }
        if (convertToBtc()) {
            Conversion convert = new Conversion();
            summary = String.format("""
                    =============🍨Dessert🍨=============
                     -Dessert Type:
                       -%s
                     -Quantity:
                       x%d
                     -Dessert Total:   ₿%.8f
                    """, getItemName(), getQuantity(), convert.getConvert(getPrice()));
        }
        return summary;
    }
}
