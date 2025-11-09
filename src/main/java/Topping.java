public class Topping {
    private String name;
    private double basePrice;
    private boolean premium;

    public Topping(String name, double basePrice, boolean premium) {
        this.name = name;
        this.basePrice = basePrice;
        this.premium = premium;
    }

    public String getName(){
        return this.name;
    }

    public double getBasePrice(){
        return this.basePrice;
    }

    public boolean isPremium(){
        return this.premium;
    }



}
