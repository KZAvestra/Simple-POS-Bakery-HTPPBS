/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sincerebakery.service;

import com.sincerebakery.entity.Product;
import com.sincerebakery.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Silka
 */
public class InventoryService {
    private final ProductRepository productRepository = new ProductRepository();

    public List<Product> getProducts() throws Exception {
        return productRepository.findAllActive();
    }

    public void saveProduct(int id, String name, String category, String priceText, String stockText) throws Exception {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama produk harus diisi.");
        }
        BigDecimal price = new BigDecimal(priceText);
        int stock = Integer.parseInt(stockText);

        if (price.compareTo(BigDecimal.ZERO) < 0 || stock < 0) {
            throw new IllegalArgumentException("Harga dan stok tidak boleh negatif.");
        }

        if (id == 0) {
            productRepository.insert(name.trim(), category.trim(), price, stock);
        } else {
            productRepository.update(id, name.trim(), category.trim(), price, stock);
        }
    }
}
