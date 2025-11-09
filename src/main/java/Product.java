public abstract class Product {

    protected String itemName;
    protected String size;
    protected int quantity;
    protected double price;

    public Product(double price, int quantity, String size, String itemName) {
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }


    public abstract double getPrice();
}
