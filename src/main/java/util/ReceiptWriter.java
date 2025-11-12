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
            //header
            bw.write("--------------------------------------------------");
            bw.newLine();
            bw.write(String.format(" %-20s %20s ","Ultimate Garlic Shop", ""));
            bw.newLine();
            String dateTime = date.format(dateFormatter) + " at " + time.format(timeFormatter);
            bw.write(String.format(" %-20s %20s ",dateTime, ""));
            bw.newLine();
            bw.write("--------------------------------------------------");
            bw.newLine();

            bw.write(order.printOrderSummary());

            //total
            bw.write("--------------------------------------------------");
            bw.newLine();
            bw.write(String.format("%-20s  %20s %.2f" , "TOTAL", "$", order.getTotalCost()));
            bw.newLine();
            bw.write("--------------------------------------------------");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
