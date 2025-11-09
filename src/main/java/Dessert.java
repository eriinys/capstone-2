public class Dessert extends Product{
    private  String type;

    public Dessert(double price, int quantity, String size, String itemName) {
        super(price, quantity, size, itemName);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    @Override
    public double getPrice() {
        return price;
    }

}
