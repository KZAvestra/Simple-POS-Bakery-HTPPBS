/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.repository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Silka
 */
public class DailyReportRepository {
    public Map<String,Object> closeDay(LocalDate date)throws Exception{
        String summary="SELECT COALESCE(SUM(total_price),0),COUNT(*) FROM transactions WHERE DATE(transaction_date)=? AND report_id IS NULL";
        try(Connection c=DatabaseConnection.getConnection()){
            try(PreparedStatement existing=c.prepareStatement("SELECT report_id,total_income,total_transactions FROM daily_reports WHERE report_date=?")){
                existing.setDate(1,java.sql.Date.valueOf(date));
                try(ResultSet r=existing.executeQuery()){
                    if(r.next()){Map<String,Object> out=new LinkedHashMap<>();out.put("date",date);out.put("income",r.getBigDecimal(2));out.put("count",r.getInt(3));out.put("id",r.getInt(1));return out;}
                }
            }
            c.setAutoCommit(false);
            try{
                BigDecimal income;int count;
                try(PreparedStatement p=c.prepareStatement(summary)){p.setDate(1,java.sql.Date.valueOf(date));try(ResultSet r=p.executeQuery()){r.next();income=r.getBigDecimal(1);count=r.getInt(2);}}
                int reportId;
                try(PreparedStatement p=c.prepareStatement("INSERT INTO daily_reports(report_date,total_income,total_transactions) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS)){
                    p.setDate(1,java.sql.Date.valueOf(date));p.setBigDecimal(2,income);p.setInt(3,count);p.executeUpdate();
                    try(ResultSet k=p.getGeneratedKeys()){k.next();reportId=k.getInt(1);}
                }
                try(PreparedStatement p=c.prepareStatement("UPDATE transactions SET report_id=? WHERE DATE(transaction_date)=? AND report_id IS NULL")){
                    p.setInt(1,reportId);p.setDate(2,java.sql.Date.valueOf(date));p.executeUpdate();
                }
                c.commit();Map<String,Object> out=new LinkedHashMap<>();out.put("date",date);out.put("income",income);out.put("count",count);out.put("id",reportId);return out;
            }catch(Exception e){c.rollback();throw e;}finally{c.setAutoCommit(true);}
        }
    }
    public List<Map<String,Object>> findAll()throws Exception{
        List<Map<String,Object>> rows=new ArrayList<>();
        String sql="SELECT report_id,report_date,total_income,total_transactions,closed_at FROM daily_reports ORDER BY report_date DESC";
        try(Connection c=DatabaseConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){
            while(r.next()){Map<String,Object> m=new LinkedHashMap<>();m.put("id",r.getInt(1));m.put("date",r.getDate(2));m.put("income",r.getBigDecimal(3));m.put("count",r.getInt(4));m.put("closed",r.getTimestamp(5));rows.add(m);}
        }return rows;
    }
}
