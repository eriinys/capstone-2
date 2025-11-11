package ui;
import model.*;

import java.util.*;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    Order order = new Order();


    public void displayMenu() {
        boolean in = true;
        while (in) {
            System.out.println("""
                    Home Screen:
                    🧄Welcome to the Ultimate Garlic Shop🧄
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
                o No
                """);
        String addTopping = scanner.nextLine();

        List<Topping> toppings = new ArrayList<>();
        if (addTopping.equalsIgnoreCase("yes")) {
            boolean inTopping = true;
            while(inTopping) {
                System.out.println("""
                        Please select your topping choice:
                        o Regular Topping
                        o Premium Topping
                        o No Topping (Classic Garlic Bread  |  $4.50)
                        """);
                String addTop = scanner.nextLine();

                if (addTop.equalsIgnoreCase("no topping")){
                    break; //ends topping option loop
                }

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
                    menu.printPremiumTopping(); //calls premium menu from Menu
                    String chosenTopping = scanner.nextLine();
                    System.out.println("""
                            Choose portion (enter number):
                            x1
                            x2
                            x3
                            """);
                    int portion = Integer.parseInt(scanner.nextLine());
                    toppings.add(new Topping(chosenTopping, isPremium, portion));
                } else {
                    System.out.println("Please choose a topping from menu below:\n");
                    menu.printRegularTopping(); //calls regular menu from Menu
                    String chosenTopping = scanner.nextLine();
                    System.out.println("""
                            Choose portion (enter number):
                            1
                            2
                            3
                            """);
                    int portion = Integer.parseInt(scanner.nextLine());
                    toppings.add(new Topping(chosenTopping, isPremium, portion));
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
        System.out.println("Enter the quantity in number format:");
        int quantity = Integer.parseInt(scanner.nextLine());

        if (addTopping.equalsIgnoreCase("yes")){
            GarlicBread gb = new GarlicBread("Garlic Bread with Topping(s)", size, quantity, breadType, isSpecialized, toppings);
            order.addItem(gb);
            System.out.println("Garlic Bread with Topping(s) added to order!🧄");
        } else {
            GarlicBread gb = new GarlicBread("Classic Garlic Bread", size, quantity, breadType, isSpecialized, toppings);
            order.addItem(gb);
            System.out.println("Classic Garlic Bread added to order!🧄");
        }
    }

    public void processAddDrink(){
        System.out.println("Please choose a drink from menu below:\n");
        menu.printDrinks(); //calls drink menu from Menu
        String chosenDrink = scanner.nextLine();

        System.out.println("""
        Please choose a size:
        o S
        o M  +$2.50
        o L  +$3.50
        """);
        String size = scanner.nextLine().toLowerCase();

        System.out.println("How many would you like (enter number)?:\n");
        int quantity = Integer.parseInt(scanner.nextLine());

        Drinks drink = new Drinks(chosenDrink, size, quantity);
        order.addItem(drink); //adds drink to order
    }

    public void processAddSide(){
        System.out.println("Please choose a side from menu below:\n");
        menu.printSides(); //calls side menu from Menu
        String chosenSide = scanner.nextLine();

        System.out.println("How many would you like (enter number)?:\n");
        int quantity = Integer.parseInt(scanner.nextLine());

        Sides side = new Sides(chosenSide, quantity);
        order.addItem(side);
    }

    public void processAddDessert(){
        System.out.println("Please choose a dessert from menu below:\n");
        menu.printDessert(); //calls dessert menu from Menu
        String chosenDessert = scanner.nextLine();

        System.out.println("How many would you like (enter number)?:\n");
        int quantity = Integer.parseInt(scanner.nextLine());

        Dessert dessert = new Dessert(chosenDessert, quantity);
        order.addItem(dessert);
    }

    public void processCheckout(){

    }

    public void processCancelOrder(){

    }
}
