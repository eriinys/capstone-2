package util;

import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class ReceiptWriter {
    private Order order;
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    LocalDateTime datetime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
    String receiptName = datetime.format(dateTimeFormatter); //create receipt file name (by date/time)

    public void saveReceipt(Order order) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/ReceiptFolder/" + receiptName))) {
            //receipt format
            int length = 0;
            for (Product product : order.getOrder()){
                String name = product.getItemName();
                if (name.length() > length) {
                    length = name.length();
                }
            }

            length += 2;

            int dashLength = length +20;
            String dash = "-".repeat(dashLength);
            String header = "| %-" + length + "s | %3s | %12s |%n";
            String line = "| %-" + length + "s | %3s | $%10.2f |%n";

            //receipt top
            bw.write(dash);
            bw.newLine();

            String shopName = "Ultimate Garlic Shop";
            bw.write(shopName);
            bw.newLine();
            //!!!!!!!!figure out how to do centering!!!!!!!!
            String dateTime = date.format(dateFormatter) + " at " + time.format(timeFormatter);
            bw.write(dateTime);
            bw.newLine();

            bw.write(dash);
            bw.newLine();

            //header
            bw.write(String.format(header,"Item", "Qty", "Price"));
            //no size
            for (Product product : order.getOrder()) {
                String name = product.getItemName();
                int qty = product.getQuantity();
                double unitPrice = product.getPrice();
                double totalPrice = unitPrice * qty;
                bw.write(String.format(line, name, qty, totalPrice));
            }

            //fix hasSize
            String base;
            for (Product product : order.getOrder()) {
                if (product.hasSize()) {
                    base = product.getItemName() + " " + product.getSize();
                    length = Math.max(length, base.length());

                    bw.write(String.format("| %s %s x%-20d | %10.2f |", product.getItemName(), product.getSize(), product.getQuantity(), product.getPrice()));
                    bw.newLine();
                } else {
                    bw.write(String.format("| %s    x%-20d | %10.2f |", product.getItemName(), product.getQuantity(), product.getPrice()));
                    bw.newLine();
                }
                //writing bread type & topping specifically for garlic bread:
                if (product instanceof GarlicBread) {
                    bw.write(String.format("| %s %s x%-20d | %10.2f |", product.getItemName(), product.getSize(), product.getQuantity(), product.getPrice()));
                    bw.newLine();
                    bw.write(String.format("|  -%s %-20s | %10s |", "Bread Type:", ((GarlicBread) product).getBreadType(), ""));
                    bw.newLine();
                    GarlicBread garlicBread = (GarlicBread) product; //downcasts model.Product to model.GarlicBread inside instanceof model.GarlicBread if statement
                    List<Topping> topping = garlicBread.getToppings();
                    if (!topping.isEmpty()) {
                        bw.write(String.format("|  %-19s | %10s |", "-Toppings:", ""));
                        bw.newLine();
                        for (Topping top : topping) {
                            bw.write(String.format("|   %s x%-18s | $%10.2f |", top.getName(), top.getPortion(), top.getTotalPrice()));
                            bw.newLine();
                        }
                    }
                    if (garlicBread.isSpecialized()) {
                        bw.write(String.format("|  %19s | %.2f", "Special Added: Cheese Stuffed Garlic Bread", garlicBread.getSpecialPrice()));
                        bw.newLine();
                    }
                }
            }

            //total
            bw.write(dash);
            bw.newLine();
            bw.write(String.format(line, "TOTAL", "", order.getTotalCost()));
            bw.newLine();
            bw.write(dash);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
