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
                    System.out.println("""
                    ⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠉₿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀₿₿₿₿₿⣿₿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀₿⣿⣿⣿⣿₿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀₿₿₿⣿⣿⣿⣿⣿₿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀₿₿₿₿⣿⣿⣿⣿⣿⣿⣿₿⢹₿₿₿⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀₿₿₿₿₿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿₿₿⠀⠀⠀⠀⠀
                    ⠀⠀⠀⢠₿₿⣿⣿⣿⣿₿⢫⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢝⠿₿₿⡄⠀⠀⠀
                    ⠀⠀⢰⣿⣿⣿⣿⣿⠟⣱⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷₿⠻⣿⡄⠀⠀
                    ⠀⠀⠸⣿⣿⣿⡿⠃⣼⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿₿⠈⠁⠀⠀
                    ⠀⠀⠀⠹⣿⣿⠃⣸⣿⣿⣿⣿⠁⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿₿⣇⠀⠀⠀
                    ⠀⠀⠀⠀⠈⠙⠀⢿⣿⣿⣿⣿₿⠹⣿⣿⣿⣿⡟⢻⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿₿⠀⠙⠿⣿⣿⣿₿⠙⠛⠿⠿⠿⠋⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀₿₿⢠₿₿₿₿⠀₿⢉₿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠈⠋⠀⠛⠀⠋⠈⠁⠀⠀⠀⠀⠀⠀
                
                """);
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
                    String itemName = Menu.getPremiumTopping().get(chosenTopping); //returns value (item name) of chosenTopping
                    double basePrice = Menu.getPremiumPrices().get(chosenTopping); //returns value (item price) of chosenTopping

                    int portion = validNumber(scanner,
                    """
                            Choose portion (enter number):
                            1
                            2
                            3
                            """);
                    Topping t = new Topping(itemName, isPremium, portion);
                    t.setBasePrice(basePrice);
                    toppings.add(t);

                } else {
                    System.out.println("Please choose a topping from menu below:\n");
                    menu.printMenu(Menu.getRegularTopping(), Menu.getRegularPrices()); //calls collection of regular topping from Menu

                    String chosenTopping = readUserInput(Menu.getRegularTopping());
                    String itemName = Menu.getRegularTopping().get(chosenTopping);
                    double basePrice = Menu.getRegularPrices().get(chosenTopping);

                    int portion = validNumber(scanner,
                            """
                                    Choose portion (enter number):
                                    1
                                    2
                                    3
                                    """);
                    Topping t = new Topping(itemName, isPremium, portion);
                    t.setBasePrice(basePrice);
                    toppings.add(t);
                }

                System.out.println("Would you like to add another topping? \n");
                String addMore = scanner.nextLine();
                while(!addMore.equalsIgnoreCase("yes") && !addMore.equalsIgnoreCase("no")){
                    System.out.println("Please enter yes or no.");
                    addMore = scanner.nextLine();
                }
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
        while(!specialized.equalsIgnoreCase("yes") && !specialized.equalsIgnoreCase("no")){
            System.out.println("Please enter yes or no.");
            specialized = scanner.nextLine();
        }
        if (specialized.equalsIgnoreCase("yes")) {
            isSpecialized = true;
        }

        int quantity = validNumber(scanner, "Enter quantity (1-3):\n");

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
        while(!size.equalsIgnoreCase("s") && !size.equalsIgnoreCase("m") && !size.equalsIgnoreCase("l")){
            System.out.println("Please enter S, M, or L");
            size = scanner.nextLine().toLowerCase();
        }

        int quantity = validNumber(scanner, "How many would you like (enter 1-3)?:\n");
        Drinks drink = new Drinks(itemName, size, quantity);
        drink.setBasePrice(basePrice);
        order.addItem(drink);
    }

    public void processAddSide(){
        System.out.println("Please choose a side from menu below:\n");
        menu.printMenu(Menu.getSideName(), Menu.getSidePrices()); //calls collection of side from Menu

        String chosenSide = readUserInput(Menu.getSideName());
        String itemName = Menu.getSideName().get(chosenSide);
        double basePrice = Menu.getSidePrices().get(chosenSide);

        int quantity = validNumber(scanner, "How many would you like (enter 1-3)?:\n");
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

        int quantity = validNumber(scanner, "How many would you like (enter 1-3)?:\n");
        Dessert dessert = new Dessert(itemName, quantity);
        dessert.setBasePrice(basePrice);
        order.addItem(dessert);
    }

    public boolean processCheckout(){
        System.out.println("""
                Proceed to checkout?
                o Yes
                o No
                """);
        String choice = scanner.nextLine();
        while(!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no")){
            System.out.println("Please enter yes or no.");
            choice = scanner.nextLine().toLowerCase();
        }
        if (choice.equalsIgnoreCase("yes")){
            System.out.println("""
                Choose a payment option:
                o Cash
                o ₿𝕚𝕥coin
                """);
            String payment = scanner.nextLine();
            while(!payment.equalsIgnoreCase("cash") && !payment.equalsIgnoreCase("bitcoin")){
                System.out.println("Please enter cash or bitcoin.");
                payment = scanner.nextLine();
            }
            if (payment.equalsIgnoreCase("Cash")){
                order.paymentMethod(false);
            }
            if (payment.equalsIgnoreCase("Bitcoin")){
                order.paymentMethod(true);
            }

            boolean validate = order.checkout();
            if (!validate) {
                System.out.println("Checkout failed.\n");
                return false; //checks if user meets the minimum purchase requirement and returns false when requirement is not met
            }

            ReceiptWriter rw = new ReceiptWriter();
            rw.saveReceipt(order);
            System.out.println("Order successfully processed!\n");
            order.cancelOrder(); //resets order for next order session
            return true;
        }
        return false;
    }

    public boolean processCancelOrder(){
        System.out.println("""
                Proceed to cancel order?
                o Yes
                o No
                """);
        String choice = scanner.nextLine();
        while(!choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("no")){
            System.out.println("Please enter yes or no.");
            choice = scanner.nextLine();
        }
        if (choice.equalsIgnoreCase("yes")){
            order.cancelOrder();
            System.out.println("Your order has been canceled.");
            return true;
        }
        return false;
    }

    //helper method to validate user input
    private String readUserInput(Map<String, String> menuChoice){
        String choice;
        while(true){
            System.out.println("Enter choice (inside []): \n");
            choice = scanner.nextLine().toUpperCase();
            if (menuChoice.containsKey(choice)){
                return choice;
            } else {
                System.out.println("Invalid input. Please enter a valid choice inside [].");
            }
        }
    }

    private static int validNumber(Scanner scanner, String prompt){
        while(true){
            System.out.println(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0 || input > 3){
                    System.err.println("Error: Value cannot be 0 or greater than 3. Please enter a number from 1-3\n");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter only number \n");
            }
        }
    }
}
