package com.example.approval;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Loads receipt items from a CSV file located in the classpath: /orders/{orderId}.csv
 * Expected columns: productName, quantity, unitPrice
 */
@Repository
public class CsvReceiptRepository implements ReceiptRepository {

    @Override
    public List<ReceiptItem> findReceiptItemsByOrderId(String orderId) {
        final String csvPath = "/orders/" + orderId + ".csv";
        final InputStream csvStream = getClass().getResourceAsStream(csvPath);

        if (Objects.isNull(csvStream)) {
            throw new IllegalArgumentException("CSV resource not found: " + csvPath);
        }

        List<ReceiptItem> parsedItems = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new InputStreamReader(csvStream, StandardCharsets.UTF_8))) {
            String currentLine;
            boolean isFirstLine = true;

            while (Objects.nonNull(currentLine = csvReader.readLine())) {
                if (isFirstLine) { // skip header row
                    isFirstLine = false;
                    continue;
                }

                String[] csvColumns = currentLine.split(",", -1);
                if (csvColumns.length < 3) continue;

                String productName = csvColumns[0].trim();
                int quantity = Integer.parseInt(csvColumns[1].trim());
                double unitPrice = Double.parseDouble(csvColumns[2].trim());

                parsedItems.add(new ReceiptItem(productName, quantity, unitPrice));
            }

        } catch (Exception exception) {
            throw new RuntimeException("Failed to read CSV for order " + orderId, exception);
        }

        return parsedItems;
    }
}
