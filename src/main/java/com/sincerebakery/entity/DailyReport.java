/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyReport {
    private final int reportId;
    private final LocalDate reportDate;
    private final BigDecimal totalIncome;
    private final int totalTransactions;
    private final LocalDateTime closedAt;

    public DailyReport(int reportId,LocalDate reportDate,BigDecimal totalIncome,int totalTransactions,LocalDateTime closedAt){
        this.reportId=reportId;this.reportDate=reportDate;this.totalIncome=totalIncome;
        this.totalTransactions=totalTransactions;this.closedAt=closedAt;
    }
    public int getReportId(){return reportId;}
    public LocalDate getReportDate(){return reportDate;}
    public BigDecimal getTotalIncome(){return totalIncome;}
    public int getTotalTransactions(){return totalTransactions;}
    public LocalDateTime getClosedAt(){return closedAt;}
}
