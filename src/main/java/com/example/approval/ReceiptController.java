package com.example.approval;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing a receipt endpoint
 */
@RestController
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(final ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    /**
     * Returns the formatted text receipt for a given order ID.
     */
    @GetMapping(value = "/api/receipts/{orderId}", produces = "text/plain; charset=UTF-8")
    public String getReceiptByOrderId(@PathVariable final String orderId) {
        return receiptService.generateReceiptText(orderId);
    }
}
