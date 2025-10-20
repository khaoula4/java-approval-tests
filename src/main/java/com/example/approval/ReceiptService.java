package com.example.approval;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptFormatter receiptFormatter;

    public ReceiptService(final ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
        this.receiptFormatter = new ReceiptFormatter();
    }

    /**
     * Retrieves receipt items for an order and formats them as text
     */
    public String generateReceiptText(final String orderId) {
        final List<ReceiptItem> receiptItems = receiptRepository.findReceiptItemsByOrderId(orderId);
        return receiptFormatter.formatReceipt(receiptItems, orderId);
    }
}
