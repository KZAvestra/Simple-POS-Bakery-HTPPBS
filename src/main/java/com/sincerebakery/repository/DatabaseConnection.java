/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.repository;

/**
 *
 * @author Silka
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new Exception("File config.properties tidak ditemukan.");
            }
            props.load(input);
        }

        return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.username"),
            props.getProperty("db.password")
        );
    }
}
