import java.util.*;

public class Order {
    private List<Product> productList = new ArrayList<>();

    public void addItem(Product product){
        productList.add(product);
    }

    public boolean hasMain(){
        return false;
    }

    public boolean hasDrinkOrSide(){
        return false;
    }

    public List<Product> getOrder(){
        return productList;
    }

    public double getTotalCost(){
        double total = 0;
        for (Product p : productList){
            total += p.getPrice();
        }
        return total;
    }

    public void checkout(){

    }

    public void cancelOrder(){

    }
}
