import java.util.*;

public class GarlicBread extends Product{
    private String breadType;
    private boolean specialized;
    private List<ToppingOption> toppings;
    private double basePrice;

    public GarlicBread(double price, int quantity, String size, String itemName, String breadType, boolean specialized) {
        super(price, quantity, size, itemName);
        this.breadType = breadType;
        this.specialized = specialized;
        this.toppings = new ArrayList<>();
        this.basePrice = 7.50;
    }

    public boolean isSpecialized() {
        return specialized;
    }

    public String getBreadType() {
        return breadType;
    }

    @Override
    public double getPrice() {
        double toppingPrice = 0;
        for (ToppingOption topping : toppings){
            toppingPrice += topping.getPrice() * topping.getPortion();
        }
        double total = toppingPrice + basePrice;
        return total;
    }

    public <List>ToppingOption getToppings() {
        return (ToppingOption) toppings;
    }


}
