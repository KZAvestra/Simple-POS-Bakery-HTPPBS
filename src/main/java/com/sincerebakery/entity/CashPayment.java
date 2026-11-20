/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

import java.math.BigDecimal;

public class CashPayment extends Payment {
    @Override public String getType() { return "Tunai"; }
    @Override public String process(BigDecimal amount) {
        return "Pembayaran " + getType() + " sebesar Rp" + amount.toPlainString() + " berhasil.";
    }
}
