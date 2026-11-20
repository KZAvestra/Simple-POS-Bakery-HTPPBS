/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.repository;

import com.sincerebakery.entity.Product;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Silka
 */
public class ProductRepository {
    public List<Product> findAllActive() throws Exception {
        List<Product> result = new ArrayList<>();
        String sql = "SELECT product_id, product_name, category, price, stock FROM products WHERE is_active=TRUE ORDER BY product_id";
        try (Connection c=DatabaseConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet r=p.executeQuery()) {
            while(r.next()) result.add(new Product(r.getInt(1),r.getString(2),r.getString(3),r.getBigDecimal(4),r.getInt(5)));
        }
        return result;
    }
    public Product findById(int id) throws Exception {
        String sql="SELECT product_id,product_name,category,price,stock FROM products WHERE product_id=? AND is_active=TRUE";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,id); try(ResultSet r=p.executeQuery()){
                return r.next()?new Product(r.getInt(1),r.getString(2),r.getString(3),r.getBigDecimal(4),r.getInt(5)):null;
            }
        }
    }
    public void insert(String name,String category,BigDecimal price,int stock)throws Exception{
        String sql="INSERT INTO products(product_name,category,price,stock,is_active) VALUES(?,?,?,?,TRUE)";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,name);p.setString(2,category);p.setBigDecimal(3,price);p.setInt(4,stock);p.executeUpdate();
        }
    }
    public void update(int id,String name,String category,BigDecimal price,int stock)throws Exception{
        String sql="UPDATE products SET product_name=?,category=?,price=?,stock=? WHERE product_id=? AND is_active=TRUE";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,name);p.setString(2,category);p.setBigDecimal(3,price);p.setInt(4,stock);p.setInt(5,id);
            if(p.executeUpdate()==0)throw new SQLException("Produk tidak ditemukan atau sudah dihapus.");
        }
    }
    public void delete(int id)throws Exception{
        String sql="UPDATE products SET is_active=FALSE WHERE product_id=? AND is_active=TRUE";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,id);if(p.executeUpdate()==0)throw new SQLException("Produk tidak ditemukan.");
        }
    }
}
