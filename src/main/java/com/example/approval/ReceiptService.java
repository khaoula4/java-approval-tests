package com.example.approval;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptFormatter receiptFormatter;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
        this.receiptFormatter = new ReceiptFormatter();
    }

    /**
     * Retrieves receipt items for an order and formats them as text
     */
    public String generateReceiptText(String orderId) {
        List<ReceiptItem> receiptItems = receiptRepository.findReceiptItemsByOrderId(orderId);
        return receiptFormatter.formatReceipt(receiptItems,orderId);
    }
}
