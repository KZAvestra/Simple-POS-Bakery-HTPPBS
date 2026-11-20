/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

import java.math.BigDecimal;

public abstract class Payment {
    public abstract String getType();
    public abstract String process(BigDecimal amount);
}
