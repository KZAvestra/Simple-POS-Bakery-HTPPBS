/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

import java.math.BigDecimal;

public class TransactionDetail {
    private final int productId;
    private final String productName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public TransactionDetail(int productId, String productName, int quantity, BigDecimal unitPrice) {
        this.productId = productId; this.productName = productName;
        this.quantity = quantity; this.unitPrice = unitPrice;
    }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getSubtotal() { return unitPrice.multiply(BigDecimal.valueOf(quantity)); }
}
