/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

import java.math.BigDecimal;

/**
 *
 * @author Silka
 */
public class Product {
    private int productId;
    private String productName;
    private String category;
    private BigDecimal price;
    private int stock;

    public Product(int productId, String productName, String category, BigDecimal price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public int getStock() { return stock; }
}
