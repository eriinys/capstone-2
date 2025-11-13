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

    public void saveReceipt(Order order) {
        String receiptName = datetime.format(dateTimeFormatter); //create receipt file name (by date/time)

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
            if (!order.isBtc()){
                bw.write("--------------------------------------------------");
                bw.newLine();
                bw.write(String.format("%-20s  %20s %.2f", "TOTAL", "$", order.getTotalCost()));
                bw.newLine();
                bw.write("--------------------------------------------------");
            } else {
                Conversion convert = new Conversion();
                bw.write("--------------------------------------------------");
                bw.newLine();
                bw.write(String.format("%-20s  %17s %.8f", "TOTAL", "₿", convert.getConvert(order.getTotalCost())));
                bw.newLine();
                bw.write("--------------------------------------------------");
            }
            bw.flush();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
