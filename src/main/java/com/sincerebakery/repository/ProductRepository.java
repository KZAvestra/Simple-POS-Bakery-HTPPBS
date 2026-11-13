/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.repository;

/**
 *
 * @author Silka
 */
import com.sincerebakery.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public List<Product> findAllActive() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT product_id, product_name, category, price, stock "
                   + "FROM products WHERE is_active = TRUE ORDER BY product_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("category"),
                    rs.getBigDecimal("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return products;
    }

    public void insert(String name, String category, java.math.BigDecimal price, int stock) throws Exception {
        String sql = "INSERT INTO products (product_name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setBigDecimal(3, price);
            ps.setInt(4, stock);
            ps.executeUpdate();
        }
    }

    public void update(int id, String name, String category, java.math.BigDecimal price, int stock) throws Exception {
        String sql = "UPDATE products SET product_name=?, category=?, price=?, stock=? "
                   + "WHERE product_id=? AND is_active=TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setBigDecimal(3, price);
            ps.setInt(4, stock);
            ps.setInt(5, id);
            ps.executeUpdate();
        }
    }
}
