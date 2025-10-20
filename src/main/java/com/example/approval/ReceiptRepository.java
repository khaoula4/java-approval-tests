package com.example.approval;

import java.util.List;

public interface ReceiptRepository {
    List<ReceiptItem> findReceiptItemsByOrderId(String orderId);
}
