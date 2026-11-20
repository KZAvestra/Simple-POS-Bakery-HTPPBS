/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.service;

import com.sincerebakery.entity.*;
import java.util.*;

/**
 *
 * @author Silka
 */
public class CartService {
    private final LinkedHashMap<Integer,CartItem> items=new LinkedHashMap<>();
    public void add(Product product,int quantity){
        if(product==null)throw new IllegalArgumentException("Produk tidak ditemukan.");
        CartItem old=items.get(product.getProductId());
        int next=(old==null?0:old.getQuantity())+quantity;
        if(quantity<1||next>product.getStock())throw new IllegalArgumentException("Jumlah melebihi stok tersedia.");
        if(old==null)items.put(product.getProductId(),new CartItem(product,next));else old.setQuantity(next);
    }
    public void remove(int id){items.remove(id);}
    public void setQuantity(int id,int qty){
        CartItem item=items.get(id);if(item==null)throw new IllegalArgumentException("Item tidak ditemukan.");
        if(qty<1)remove(id);else item.setQuantity(qty);
    }
    public List<CartItem> getItems(){return new ArrayList<>(items.values());}
    public void clear(){items.clear();}
}
