/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sincerebakery.service;

import com.sincerebakery.repository.DailyReportRepository;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Silka
 */
public class ReportService {
    private final DailyReportRepository repository=new DailyReportRepository();
    public Map<String,Object> closeToday()throws Exception{return repository.closeDay(LocalDate.now());}
    public List<Map<String,Object>> getReports()throws Exception{return repository.findAll();}
}
