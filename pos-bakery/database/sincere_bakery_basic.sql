CREATE DATABASE IF NOT EXISTS sincere_bakery;
USE sincere_bakery;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('CASHIER', 'STOCK_MANAGER') NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DECIMAL(12,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO users (username, password, full_name, role)
SELECT 'cashier', 'cashier123', 'Cashier Demo', 'CASHIER'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'cashier');

INSERT INTO users (username, password, full_name, role)
SELECT 'stock', 'stock123', 'Stock Manager Demo', 'STOCK_MANAGER'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'stock');

INSERT INTO products (product_name, category, price, stock)
SELECT 'Roti Cokelat', 'Roti', 12000, 20
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_name = 'Roti Cokelat');

INSERT INTO products (product_name, category, price, stock)
SELECT 'Croissant', 'Pastry', 18000, 15
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_name = 'Croissant');

INSERT INTO products (product_name, category, price, stock)
SELECT 'Donat Gula', 'Donat', 8000, 25
WHERE NOT EXISTS (SELECT 1 FROM products WHERE product_name = 'Donat Gula');
