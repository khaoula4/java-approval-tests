package com.example.approval;

import java.util.List;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

class ReceiptFormatterTests {

    @Test
    void whenFormatReceipt() {
        final List<ReceiptItem> sampleItems = List.of(
                new ReceiptItem("Tea", 2, 1.50),
                new ReceiptItem("Coffee", 1, 3.20),
                new ReceiptItem("Cookie", 3, 0.80)
        );
        final String orderId = "500";
        final ReceiptFormatter receiptFormatter = new ReceiptFormatter();
        final String generatedReceiptText = receiptFormatter.formatReceipt(sampleItems, orderId);

        Approvals.verify(generatedReceiptText);
    }

    @Test
    void whenFormatReceipt_givenCombinations() {
        final String[] orderIds = { "500", "600" };

        final List<ReceiptItem> itemsA = List.of(
                new ReceiptItem("Tea", 2, 1.50),
                new ReceiptItem("Cookie", 3, 0.80));

        final List<ReceiptItem> itemsB = List.of(
                new ReceiptItem("Coffee", 1, 3.20),
                new ReceiptItem("Muffin", 2, 2.10));

        final List<List<ReceiptItem>> typeSafeList = List.of(itemsA, itemsB);
        @SuppressWarnings("unchecked") final List<ReceiptItem>[] itemSets = typeSafeList.toArray(new List[0]);

        final ReceiptFormatter receiptFormatter = new ReceiptFormatter();

        CombinationApprovals.verifyAllCombinations(
                (String orderId, List<ReceiptItem> items) -> receiptFormatter.formatReceipt(items, orderId), orderIds, itemSets);

    }
}
