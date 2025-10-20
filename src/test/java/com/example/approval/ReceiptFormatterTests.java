package com.example.approval;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.List;


class ReceiptFormatterTests {

    @Test
    void shouldFormatReceiptTextConsistently() {
        final List<ReceiptItem> sampleItems = List.of(
                new ReceiptItem("Tea", 2, 1.50),
                new ReceiptItem("Coffee", 1, 3.20),
                new ReceiptItem("Cookie", 3, 0.80)
        );
        String orderId = "500";
        final ReceiptFormatter receiptFormatter = new ReceiptFormatter();
        final String generatedReceiptText = receiptFormatter.formatReceipt(sampleItems, orderId);

        Approvals.verify(generatedReceiptText);
    }
}
