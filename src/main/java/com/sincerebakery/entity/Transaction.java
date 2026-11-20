/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    private final int transactionId;
    private final LocalDateTime transactionDate;
    private final BigDecimal totalPrice;
    private final String paymentType;
    private final List<TransactionDetail> details;

    public Transaction(int transactionId,LocalDateTime transactionDate,
                       BigDecimal totalPrice,String paymentType,List<TransactionDetail> details){
        this.transactionId=transactionId;this.transactionDate=transactionDate;
        this.totalPrice=totalPrice;this.paymentType=paymentType;this.details=List.copyOf(details);
    }
    public int getTransactionId(){return transactionId;}
    public LocalDateTime getTransactionDate(){return transactionDate;}
    public BigDecimal getTotalPrice(){return totalPrice;}
    public String getPaymentType(){return paymentType;}
    public List<TransactionDetail> getDetails(){return details;}
}
