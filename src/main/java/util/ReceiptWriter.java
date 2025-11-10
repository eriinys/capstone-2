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

    public void saveReceipt(Order order){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/ReceiptFolder/" + receiptName))){

            String line = "----------------------------------------";

            bw.write(line);
            bw.newLine();
            bw.write("     Ultimate Garlic Shop     " +
                    "\n     " + date.format(dateFormatter) + " at " + time.format(timeFormatter) + "     ");
            bw.newLine();
            bw.write(line);
            bw.newLine();

            bw.write(String.format("| %-20s | %10s |", "Item","Price"));
            bw.newLine();

            for (Product product : order.getOrder()){
                if (product.hasSize()){
                    bw.write(String.format("| %s %s x%-20d | %10.2f |", product.getItemName(), product.getSize(), product.getQuantity() ,product.getPrice()));
                    bw.newLine();
                } else {
                    bw.write(String.format("| %s    x%-20d | %10.2f |", product.getItemName(), product.getQuantity() ,product.getPrice()));
                    bw.newLine();
                }
                //writing bread type & topping specifically for garlic bread:
                if (product instanceof GarlicBread){
                    bw.write(String.format("| %s %s x%-20d | %10.2f |", product.getItemName(), product.getSize(), product.getQuantity() ,product.getPrice()));
                    bw.newLine();
                    bw.write(String.format("|  -%s %-20s | %10s |", "Bread Type:", ((GarlicBread) product).getBreadType(), ""));
                    bw.newLine();
                    GarlicBread garlicBread = (GarlicBread) product; //downcasts model.Product to model.GarlicBread inside instanceof model.GarlicBread if statement
                    List<Topping> topping = garlicBread.getToppings();
                    if(!topping.isEmpty()){
                        bw.write(String.format("|  %-19s | %10s |", "-Toppings:", ""));
                        bw.newLine();
                        for (Topping top : topping){
                            bw.write(String.format("|   %s x%-18s | $%10.2f |", top.getName(), top.getPortion(), top.getTotalPrice()));
                            bw.newLine();
                        }
                    }
                    if (garlicBread.isSpecialized()){
                        bw.write(String.format("|  %19s | %.2f", "Special Added: Cheese Stuffed Garlic Bread"));
                        bw.newLine();
                    }
                }
            }
            bw.write(line);
            bw.newLine();
            bw.write(String.format("| %-20s | $%10.2f |", "TOTAL", order.getTotalCost()));
            bw.newLine();
            bw.write(line);


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
