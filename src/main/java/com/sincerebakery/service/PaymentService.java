/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.service;

import com.sincerebakery.entity.*;
import java.math.BigDecimal;

/**
 *
 * @author Silka
 */
public class PaymentService {
    public Payment createPayment(String type){
        if(type==null)throw new IllegalArgumentException("Metode pembayaran harus dipilih.");
        return switch(type.trim().toUpperCase()){
            case "TUNAI" -> new CashPayment();
            case "QRIS" -> new QRISPayment();
            case "DEBIT" -> new DebitPayment();
            default -> throw new IllegalArgumentException("Metode pembayaran tidak dikenal.");
        };
    }
    public String process(Payment payment,BigDecimal amount){
        if(payment==null||amount==null||amount.signum()<=0)throw new IllegalArgumentException("Pembayaran tidak valid.");
        return payment.process(amount);
    }
}
