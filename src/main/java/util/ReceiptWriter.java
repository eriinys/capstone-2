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
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    String receiptName = datetime.format(dateTimeFormatter); //create receipt file name (by date/time)

    public void saveReceipt(Order order) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/ReceiptFolder/" + receiptName))) {
            //receipt format
            int length = 0; //length will hold maximum text length
            for (Product product : order.getOrder()) {
                String name = product.getItemName();
                if (name != null) {
                    length = Math.max(length, name.length()); //compares current item length with previous one
                }
                if(product instanceof GarlicBread){
                    GarlicBread gb = (GarlicBread) product;
                    length = Math.max(length, "Bread Type:".length());

                    String breadName = gb.getBreadType();
                    if (breadName != null){
                        length = Math.max(length, breadName.length());
                    }

                    List<Topping> toppings = gb.getToppings();
                    if(toppings != null && !toppings.isEmpty()) {
                        length = Math.max(length, "Toppings:".length());
                        for (Topping t : toppings){
                            if (t.getName() != null){
                                length = Math.max(length, t.getName().length());
                            }
                        }
                    }
                    if (gb.isSpecialized()) {
                        length = Math.max(length, "Special: Cheese Stuffed Garlic Bread".length());
                    }
                }
            }

            length += 2;

            int dashLength = length + 35;
            String dash = "-".repeat(dashLength);
            String header = "| %-" + length + "s | %3s | %3s | %12s |%n";
            String line = "| %-" + length + "s | %3s |  %3s | %10s |%n";
            String totalLine = "| %-" + length + "s %28s |";

            //receipt top
            bw.write(dash);
            bw.newLine();

            String shopName = " %-20s %20s ";
            bw.write(String.format(shopName,"Ultimate Garlic Shop", ""));
            bw.newLine();
            String dateTime = date.format(dateFormatter) + " at " + time.format(timeFormatter);

            bw.write(String.format(shopName,dateTime, ""));
            bw.newLine();
            bw.write(dash);
            bw.newLine();

            //header
            bw.write(String.format(header, "Item", "Size", "Qty", "Price"));
            bw.write(dash);
            bw.newLine();

            for (Product product : order.getOrder()) {
                String name = product.getItemName();
                String size = product.getSize();
                int qty = product.getQuantity();
                double basePrice = product.getBasePrice();
                double basePlusSize = basePrice + product.getSizePrice();
                double unitPrice = product.getPrice();
                double totalPrice = unitPrice * qty;
                String price = String.format("%.2f",totalPrice);

                if (product.hasSize() && !(product instanceof  GarlicBread)){
                    bw.write(String.format(line, name, size.toUpperCase(), qty, basePlusSize));
                    bw.newLine();
                }

                else {
                    bw.write(String.format(line, name, " ", qty, price));
                    bw.newLine();
                }
            }

            //total
            bw.write(dash);
            bw.newLine();
            bw.write(String.format(totalLine, "TOTAL", order.getTotalCost()));
            bw.newLine();
            bw.write(dash);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
