package com.example.approval;

import java.util.List;

public class ReceiptFormatter {

    /**
     * Formats the provided items into a human-readable receipt string
     */
    public String formatReceipt(final List<ReceiptItem> purchasedItems, final String orderId) {
        final String lineSep = System.lineSeparator();
        final StringBuilder receiptBuilder = new StringBuilder("RECEIPT FOR ORDER ");
        receiptBuilder.append("%s :".formatted(orderId));
        receiptBuilder.append(lineSep);
        receiptBuilder.append(lineSep);
        double totalAmount = 0.0;

        for (final ReceiptItem currentItem : purchasedItems) {
            final double lineTotal = currentItem.quantity() * currentItem.unitPrice();
            totalAmount += lineTotal;
            receiptBuilder.append(
                    "%s x%d @ %.2f = %.2f"
                            .formatted(currentItem.productName(),
                                    currentItem.quantity(),
                                    currentItem.unitPrice(),
                                    lineTotal)
            );
            receiptBuilder.append(lineSep);
        }
        receiptBuilder.append("TOTAL = ").append(String.format("%.2f", totalAmount)).append(lineSep);
        return receiptBuilder.toString();
    }
}
