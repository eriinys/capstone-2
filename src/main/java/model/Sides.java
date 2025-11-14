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
        String summary = "";
        if (!convertToBtc()) {
            summary = String.format("""
                    =============🥗Side🥗=============
                     -Side Type:
                       -%s
                     -Quantity:
                       x%d
                     -Side Total:   $%.2f
                    """, getItemName(), getQuantity(), getPrice());
        }
        if (convertToBtc()) {
            Conversion convert = new Conversion();
            summary = String.format("""
                    =============🥗Side🥗=============
                     -Side Type:
                       -%s
                     -Quantity:
                       x%d
                     -Side Total:   ₿%.8f
                    """, getItemName(), getQuantity(), convert.getConvert(getPrice()));
        }
        return summary;
    }


}
