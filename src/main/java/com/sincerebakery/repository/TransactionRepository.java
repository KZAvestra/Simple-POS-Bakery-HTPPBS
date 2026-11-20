/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.repository;

import com.sincerebakery.entity.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Silka
 */
public class TransactionRepository {
    public int save(List<CartItem> items, String paymentType, int userId) throws Exception {
        if(items==null || items.isEmpty()) throw new IllegalArgumentException("Keranjang masih kosong.");
        String txSql = "INSERT INTO transactions(user_id, total_price, payment_type) VALUES(?,?,?)";
        String stockSql="UPDATE products SET stock=stock-? WHERE product_id=? AND is_active=TRUE AND stock>=?";
        String detailSql="INSERT INTO transaction_details(transaction_id,product_id,product_name,quantity,unit_price,subtotal) VALUES(?,?,?,?,?,?)";
        try(Connection c=DatabaseConnection.getConnection()){
            c.setAutoCommit(false);
            try{
                BigDecimal total=BigDecimal.ZERO;
                for(CartItem item:items) total=total.add(item.getSubtotal());
                int txId;
                try(PreparedStatement p=c.prepareStatement(txSql,Statement.RETURN_GENERATED_KEYS)){
                    p.setInt(1, userId);
                    p.setBigDecimal(2, total);
                    p.setString(3, paymentType);
                    p.executeUpdate();
                    try(ResultSet keys=p.getGeneratedKeys()){if(!keys.next())throw new SQLException("ID transaksi gagal dibuat.");txId=keys.getInt(1);}
                }
                try(PreparedStatement stock=c.prepareStatement(stockSql);PreparedStatement detail=c.prepareStatement(detailSql)){
                    for(CartItem item:items){
                        Product product=item.getProduct();
                        stock.setInt(1,item.getQuantity());
                        stock.setInt(2,product.getProductId());
                        stock.setInt(3,item.getQuantity());
                        if(stock.executeUpdate()!=1)throw new SQLException("Stok "+product.getProductName()+" tidak mencukupi. Muat ulang inventory.");
                        detail.setInt(1,txId);
                        detail.setInt(2,product.getProductId());
                        detail.setString(3,product.getProductName());
                        detail.setInt(4,item.getQuantity());
                        detail.setBigDecimal(5,product.getPrice());
                        detail.setBigDecimal(6,item.getSubtotal());
                        detail.addBatch();
                    }
                    detail.executeBatch();
                }
                c.commit();return txId;
            }catch(Exception ex){c.rollback();throw ex;}finally{c.setAutoCommit(true);}
        }
    }
    public List<Map<String,Object>> findAll() throws Exception {
        List<Map<String,Object>> rows=new ArrayList<>();
        String sql="SELECT transaction_id,transaction_date,total_price,payment_type FROM transactions ORDER BY transaction_date DESC";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){
            while(r.next()){Map<String,Object> m=new LinkedHashMap<>();m.put("id",r.getInt("transaction_id"));m.put("date",r.getTimestamp("transaction_date"));m.put("total",r.getBigDecimal("total_price"));m.put("payment",r.getString("payment_type"));rows.add(m);}
        }return rows;
    }
    public List<Map<String,Object>> findDetails(int transactionId)throws Exception{
        List<Map<String,Object>> rows=new ArrayList<>();
        String sql="SELECT product_name,quantity,unit_price,subtotal FROM transaction_details WHERE transaction_id=? ORDER BY detail_id";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,transactionId);try(ResultSet r=p.executeQuery()){while(r.next()){
                Map<String,Object> m=new LinkedHashMap<>();m.put("name",r.getString(1));m.put("qty",r.getInt(2));m.put("price",r.getBigDecimal(3));m.put("subtotal",r.getBigDecimal(4));rows.add(m);
            }}
        }return rows;
    }
}
