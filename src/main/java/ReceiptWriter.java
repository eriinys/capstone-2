import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ReceiptWriter {
    private Order order;
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    public void saveReceipt(Order order){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/ReceiptFolder/ReceiptFile.csv"))){

            String line = "----------------------------------------";

            bw.write(line);
            bw.newLine();
            bw.write("     Ultimate Garlic Shop     " +
                    "\n     " + date.format(dateFormatter) + " at " + time.format(timeFormatter) + "     ");
            bw.newLine();
            bw.write(line);

            bw.write(String.format("| %-20s | %10s |", "Item","Price"));
            for (Product product : order.getOrder()){
                bw.write(String.format("| %s %s x%-10d | %10.2f |", product.itemName, product.getSize(), product.getQuantity() ,product.getPrice()));
                if (product instanceof GarlicBread){
                    bw.write(String.format("| %s %s x%-10d | %10.2f |", product.itemName, product.getSize(), product.getQuantity() ,product.getPrice()));
                    bw.write(String.format("|  -%s %-10s | %10s |", "Bread Type:", ((GarlicBread) product).getBreadType(), ""));
                    bw.write(String.format("|  -%-15s | %10s |","Toppings:", " "));

                }
            }
            bw.write(line);
            bw.write(String.format("| %-20s | %10.2f |", "TOTAL", order.getTotalCost()));
            bw.write(line);


        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
