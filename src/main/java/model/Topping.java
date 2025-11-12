package model;

public class Topping {
    private String name;
    private double basePrice;
    private boolean premium;
    private int portion;

    public Topping(String name, boolean premium, int portion) {
        this.name = name;
        this.premium = premium;
        this.portion = portion;
    }

    public String getName(){
        return name;
    }

    public double getBasePrice(){
        return basePrice;
    }
    public void setBasePrice(double bp){
        basePrice = bp;
    }

    public boolean isPremium(){
        return premium;
    }

    public int getPortion() {
        return portion;
    }

    //Add price calculation for size

    public double getTotalPrice(){
        double total;
        if (portion <= 0){
            total = 0.00;
        } else {
            total = getBasePrice() * portion;
        }
        return total;
    }
}
