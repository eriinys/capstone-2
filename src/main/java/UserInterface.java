import java.util.*;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Order order = new Order();

    public void displayMenu() {
        boolean in = true;
        while (in) {
            System.out.println("""
                    Home Screen:
                    Welcome to the Ultimate Garlic Shop!
                    Please choose from following options:
                    1) Place New Order
                    0) Exit
                    """);
            int choice1 = Integer.parseInt(scanner.nextLine());
            switch(choice1){
                case 1 -> {
                    System.out.println("""
                            Order Screen:
                            1) Add Garlic Bread
                            2) Add Drink
                            3) Add Side
                            4) Add Dessert
                            5) Checkout
                            0) Cancel Order
                            """);
                    int choice2 = Integer.parseInt(scanner.nextLine());
                    switch(choice2){
                        case 1 -> processAddItem();
                        case 2 -> processAddDrink();
                        case 3 -> processAddSide();
                        case 4 -> processAddDessert();
                        case 5 -> processCheckout();
                        case 0 -> processCancelOrder();
                    }
                }
                case 2 -> {
                    System.out.println("May your day be full of garlic!");
                    in = false;
                }
            }

        }
    }

    public void processAddItem(){
        System.out.println("""
                Please select your bread type:
                o French Baguette
                o Sourdough
                """);
        String breadType = scanner.nextLine().toLowerCase();
        System.out.println("""
                Please select your topping choice:
                o Regular Topping
                o Premium Topping
                """);
        boolean isPremium;
        if(scanner.nextLine().toLowerCase().contains("regular")){
            isPremium = false;
        } else if (scanner.nextLine().toLowerCase().contains("premium")){
            isPremium = true;
        } else {
            System.out.println("Please enter either Regular or Premium.");
        }
        if (isPremium = false){
            System.out.println("""
                    Please pick from the following toppings:
                    o Garlic Mushroom
                    o Caramelized Garlic
                    o Pickled Garlic
                    o Roasted Garlic
                    """);
        }
    }

    public void processAddDrink(){

    }

    public void processAddSide(){

    }

    public void processAddDessert(){

    }

    public void processCheckout(){

    }

    public void processCancelOrder(){

    }
}
