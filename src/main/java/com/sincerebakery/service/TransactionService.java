/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.service;

import com.sincerebakery.entity.*;
import com.sincerebakery.repository.TransactionRepository;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Silka
 */
public class TransactionService {
    private final TransactionRepository repository=new TransactionRepository();
    public int checkout(List<CartItem> items, Payment payment, int userId) throws Exception {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Keranjang masih kosong.");
        }

        BigDecimal total = items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        payment.process(total);

        return repository.save(items, payment.getType(), userId);
    }
    public List<Map<String,Object>> getHistory()throws Exception{return repository.findAll();}
    public List<Map<String,Object>> getDetails(int id)throws Exception{return repository.findDetails(id);}
}
