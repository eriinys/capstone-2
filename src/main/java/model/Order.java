package model;
import java.util.*;

public class Order {
    private List<Product> productList = new ArrayList<>();

    public void addItem(Product product) {
        productList.add(product);
    }

    public boolean hasMain(){
        for (Product p : productList){
            if (p instanceof GarlicBread){
                return true;
            }
        }
        return false;
    }

    public boolean hasDrinkOrSide() {
        for (Product p : productList){
            if (p instanceof Drinks || p instanceof Sides || p instanceof Dessert) {
                return true;
            }
        }
        return false;
    }

    public List<Product> getOrder() {
        return productList;
    }

    public double getTotalCost() {
        double total = 0;
        for (Product p : productList) {
            total += p.getPrice();
        }
        return total;
    }

    public void checkout() {
        if (productList.isEmpty()){
            System.out.println("Order is empty. Please add at least one side, drink, dessert or garlic bread.");
            return;
        }

        boolean meetRequirement = hasMain() || hasDrinkOrSide(); //sets rule to check for requirement

        if (meetRequirement) {
            for (Product p : productList) {
                p.getSummary();
            }
        } else {
            System.out.println("Minimum requirement for checkout not met:\n" +
                    "Must order a side, drink, or dessert.");
        }
    }

    public void cancelOrder() {
        productList.clear();
    }

}
