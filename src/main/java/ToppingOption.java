public class ToppingOption {
    private Topping topping;
    private int portion;

    public ToppingOption(Topping topping, int portion) {
        this.topping = topping;
        this.portion = portion;
    }

    public Topping getTopping() {
        return topping;
    }

    public int getPortion() {
        return portion;
    }

    public double getPrice(){
        double total = topping.getBasePrice() * portion;
        if (portion == 0){
            total = 0;
        }
        return total;
    }
}
