/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

/**
 *
 * @author Silka
 */
public class User {
    private final int userId;
    private final String username;
    private final String fullName;
    private final String role;

    public User(int userId, String username, String fullName, String role) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public boolean isCashier() { return "CASHIER".equals(role); }
    public boolean isStockManager() { return "STOCK_MANAGER".equals(role); }
}
