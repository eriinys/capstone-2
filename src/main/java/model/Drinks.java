package model;

public class Drinks extends Product{
    private String size;

    public Drinks(String itemName, String size, int quantity, double price) {
        super(itemName, quantity, price);
        this.size = size;
    }

    @Override
    public boolean hasSize(){
        return true; //override hasSize to true for product with size
    }

    @Override
    public String getSize(){
        return size; //overrides default getSize method's empty string
    }

    @Override
    public double getPrice() {
        double sizePrice = 0;
        if (size.equalsIgnoreCase("s")){
            sizePrice = 0.00;
        } else if (size.equalsIgnoreCase("m")){
            sizePrice = 2.50;
        } else if (size.equalsIgnoreCase("l")){
            sizePrice = 3.50;
        }
        return price += sizePrice;
    }

    @Override
    public String getSummary() {
        return String.format("%s  %s  x%d    +$%.2f", getItemName(), getSize() ,getQuantity(), getPrice());
    }

}
