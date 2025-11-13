package ui;
import model.*;
import util.ReceiptWriter;

import java.util.*;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    Order order = new Order();

    public void displayMenu() {
        boolean in = true;
        while (in) {
            System.out.println("""
                                    -Home Screen-
                    ===🧄Welcome to the Ultimate Garlic Shop🧄===
                    ==============𝕎𝕖 𝕒𝕔𝕔𝕖𝕡𝕥 ₿𝕚𝕥𝕔𝕠𝕚𝕟!==============
                                    ⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀
                                    ⠸⠿⣿⣿⣿⡿⠿⠿⣿⣿⣿⣶⣄⠀
                                    ⠀⠀⢸⣿⣿⡇⠀⠀⠀⠈⣿⣿⣿⠀
                                    ⠀⠀⢸⣿⣿⡇⠀⠀⢀⣠⣿⣿⠟⠀
                                    ⠀⠀⢸⣿⣿⡿⠿⠿⠿⣿⣿⣥⣄⠀
                                    ⠀⠀⢸⣿⣿⡇⠀⠀⠀⠀⢻⣿⣿⣧
                                    ⠀⠀⢸⣿⣿⡇⠀⠀⠀⠀⣼⣿⣿⣿
                                    ⢰⣶⣿⣿⣿⣷⣶⣶⣾⣿⣿⠿⠛⠁
                                    ⠀⠀⠀⠀⣿⡇⠀⢸⣿⡇⠀⠀⠀⠀
                    ==============================================
                    Please choose from following options:
                    1) Place New Order
                    0) Exit
                    """);
            int choice1 = Integer.parseInt(scanner.nextLine());
            switch(choice1){
                case 1 -> {
                    boolean ordering = true;
                    while (ordering) {
                        System.out.println("""
                                ===Order Screen===
                                1) Add Garlic Bread
                                2) Add Drink
                                3) Add Side
                                4) Add Dessert
                                5) Checkout
                                0) Cancel Order
                                """);
                        int choice2 = Integer.parseInt(scanner.nextLine());
                        switch (choice2) {
                            case 1 -> processAddItem();
                            case 2 -> processAddDrink();
                            case 3 -> processAddSide();
                            case 4 -> processAddDessert();
                            case 5 -> {
                                boolean checkout = processCheckout();
                                if (checkout) {
                                    ordering = false;
                                }
                            }
                            case 0 -> {
                                boolean cancel = processCancelOrder();
                                if (cancel) {
                                ordering = false;
                                }
                            }
                        }
                    }
                }
                case 0 -> {
                    System.out.println("===🧄May your day be full of garlic!🧄===");
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
        String breadType = scanner.nextLine().toLowerCase(); //get bread type
        while(!breadType.contains("baguette") && !breadType.contains("sourdough")){
            System.out.println("Please enter either French Baguette or Sourdough");
            breadType = scanner.nextLine().toLowerCase();
        } //catch invalid input

        System.out.println("""
        Please choose a size:
        o S
        o M  +$2.00
        o L  +$3.25
        """);
        String size = scanner.nextLine().toLowerCase(); //get size
        while(!size.equalsIgnoreCase("s") && !size.equalsIgnoreCase("m") && !size.equalsIgnoreCase("l")){
            System.out.println("Please enter S, M, or L");
            size = scanner.nextLine().toLowerCase();
        } //catch invalid input

        System.out.println("""
                Would you like to add topping?:
                o Yes
                o No (Classic Garlic Bread  |  $4.50)
                """);
        String addTopping = scanner.nextLine();

        while(!addTopping.equalsIgnoreCase("yes") && !addTopping.equalsIgnoreCase("no")){
            System.out.println("Invalid input. Please enter yes or no: ");
            addTopping = scanner.nextLine();
        }

        List<Topping> toppings = new ArrayList<>();
        if (addTopping.equalsIgnoreCase("yes")) {
            boolean inTopping = true;
            while(inTopping) {
                System.out.println("""
                        Please select your topping choice:
                        o Regular Topping
                        o Premium Topping
                        """);
                String addTop = scanner.nextLine();

                boolean isPremium;
                if (addTop.toLowerCase().contains("premium")) {
                    isPremium = true;
                } else if (addTop.toLowerCase().contains("regular")) {
                    isPremium = false;
                } else {
                    System.out.println("Please enter either Regular or Premium.");
                    continue;
                }

                if (isPremium) {
                    System.out.println("Please choose a topping from menu below:\n");
                    menu.printMenu(Menu.getPremiumTopping(), Menu.getPremiumPrices()); //calls collection of premium topping from Menu

                    String chosenTopping = readUserInput(Menu.getPremiumTopping());
                    String itemName = Menu.getPremiumTopping().get(chosenTopping);
                    double basePrice = Menu.getPremiumPrices().get(chosenTopping);

                    System.out.println("""
                            Choose portion (enter number):
                            1
                            2
                            3
                            """);
                    int portion = Integer.parseInt(scanner.nextLine());
                    Topping t = new Topping(itemName, isPremium, portion);
                    t.setBasePrice(basePrice);
                    toppings.add(t);

                } else {
                    System.out.println("Please choose a topping from menu below:\n");
                    menu.printMenu(Menu.getRegularTopping(), Menu.getRegularPrices()); //calls collection of regular topping from Menu

                    String chosenTopping = readUserInput(Menu.getRegularTopping());
                    String itemName = Menu.getRegularTopping().get(chosenTopping);
                    double basePrice = Menu.getRegularPrices().get(chosenTopping);

                    System.out.println("""
                            Choose portion (enter number):
                            1
                            2
                            3
                            """);
                    int portion = Integer.parseInt(scanner.nextLine());
                    Topping t = new Topping(itemName, isPremium, portion);
                    t.setBasePrice(basePrice);
                    toppings.add(t);
                }

                System.out.println("Would you like to add another toppings?");
                String addMore = scanner.nextLine();
                if (addMore.equalsIgnoreCase("no")){
                    inTopping = false; //ends topping loop
                }
            }
        }

        boolean isSpecialized = false;
        System.out.println("""
                Would you like to upgrade to Cheese Stuffed Garlic Bread (+$2.50)?
                o Yes
                o No
                """);
        String specialized = scanner.nextLine();
        if (specialized.equalsIgnoreCase("yes")) {
            isSpecialized = true;
        }
        System.out.println("Enter quantity (number):");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (addTopping.equalsIgnoreCase("yes")){
            GarlicBread gb = new GarlicBread("Garlic Bread with Topping(s)", size, quantity, breadType, isSpecialized, toppings);
            order.addItem(gb);
            System.out.println("Garlic Bread with Topping(s) added to order!🧄\n");
        } else {
            GarlicBread gb = new GarlicBread("Classic Garlic Bread", size, quantity, breadType, isSpecialized, toppings);
            order.addItem(gb);
            System.out.println("Classic Garlic Bread added to order!🧄\n");
        }
    }

    public void processAddDrink(){
        System.out.println("Please choose a drink from menu below:\n");
        menu.printMenu(Menu.getDrinkName(), Menu.getDrinkPrices()); //calls collection of drink from Menu

        String chosenDrink = readUserInput(Menu.getDrinkName());
        String itemName = Menu.getDrinkName().get(chosenDrink);
        double basePrice = Menu.getDrinkPrices().get(chosenDrink);

        System.out.println("""
        Please choose a size:
        o S
        o M  +$2.50
        o L  +$3.50
        """);
        String size = scanner.nextLine().toLowerCase();

        System.out.println("How many would you like (enter number)?:\n");
        int quantity = Integer.parseInt(scanner.nextLine());

        Drinks drink = new Drinks(itemName, size, quantity);
        drink.setBasePrice(basePrice);
        order.addItem(drink); //adds drink to order
    }

    public void processAddSide(){
        System.out.println("Please choose a side from menu below:\n");
        menu.printMenu(Menu.getSideName(), Menu.getSidePrices()); //calls collection of side from Menu

        String chosenSide = readUserInput(Menu.getSideName());
        String itemName = Menu.getSideName().get(chosenSide);
        double basePrice = Menu.getSidePrices().get(chosenSide);

        System.out.println("How many would you like (enter number)?:\n");
        int quantity = Integer.parseInt(scanner.nextLine());

        Sides side = new Sides(itemName, quantity);
        side.setBasePrice(basePrice);
        order.addItem(side);
    }

    public void processAddDessert(){
        System.out.println("Please choose a dessert from menu below:\n");
        menu.printMenu(Menu.getDessertName(), Menu.getDessertPrices()); //calls collection of dessert from Menu

        String chosenDessert = readUserInput(Menu.getDessertName());
        String itemName = Menu.getDessertName().get(chosenDessert);
        double basePrice = Menu.getDessertPrices().get(chosenDessert);

        System.out.println("How many would you like (enter number)?:\n");
        int quantity = Integer.parseInt(scanner.nextLine());

        Dessert dessert = new Dessert(itemName, quantity);
        dessert.setBasePrice(basePrice);
        order.addItem(dessert);
    }

    public boolean processCheckout(){
        boolean validate = order.checkout();
        if (!validate) {
            System.out.println("Checkout failed.\n");
            return false; //checks if user meets the minimum purchase requirement and returns false when requirement is not met
        }
        System.out.println("""
                Proceed to checkout?
                o Yes
                o No
                """);
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equalsIgnoreCase("yes")){
            ReceiptWriter rw = new ReceiptWriter();
            rw.saveReceipt(order);
            System.out.println("Order successfully processed!\n");
            order.cancelOrder(); //resets order for next user session
            return true;
        }
        while(!choice.contains("yes") && !choice.contains("no")){
            System.out.println("Please enter yes or no.");
            choice = scanner.nextLine().toLowerCase();
        }
        return false;
    }

    public boolean processCancelOrder(){
        System.out.println("""
                Proceed to cancel order?
                o Yes
                o No
                """);
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equalsIgnoreCase("yes")){
            order.cancelOrder();
            System.out.println("Your order has been canceled.");
            return true;
        }
        while(!choice.contains("yes") && !choice.contains("no")){
            System.out.println("Please enter yes or no.");
            choice = scanner.nextLine().toLowerCase();
        }
        return false;
    }

    //helper method to validate user input
    private String readUserInput(Map<String, String> menuChoice){
        String choice;
        while(true){
            System.out.println("Enter choice (inside []): ");
            choice = scanner.nextLine().toUpperCase();
            if (menuChoice.containsKey(choice)){
                return choice;
            } else {
                System.out.println("Invalid input. Please enter a valid choice inside [].");
            }
        }
    }
}
