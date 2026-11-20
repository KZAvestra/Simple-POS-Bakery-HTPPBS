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
    public List<Product> getProducts() throws Exception { return productRepository.findAllActive(); }
    public Product getProduct(int id)throws Exception{return productRepository.findById(id);}
    public void deleteProduct(int id)throws Exception{productRepository.delete(id);}
    public void saveProduct(int id,String name,String category,String priceText,String stockText)throws Exception{
        if(name==null||name.isBlank())throw new IllegalArgumentException("Nama produk harus diisi.");
        if(priceText==null||priceText.isBlank())throw new IllegalArgumentException("Harga harus diisi.");
        if(stockText==null||stockText.isBlank())throw new IllegalArgumentException("Stok harus diisi.");
        BigDecimal price;int stock;
        try{price=new BigDecimal(priceText.trim());}catch(NumberFormatException e){throw new IllegalArgumentException("Harga harus berupa angka yang valid.");}
        try{stock=Integer.parseInt(stockText.trim());}catch(NumberFormatException e){throw new IllegalArgumentException("Stok harus berupa bilangan bulat.");}
        if(price.compareTo(BigDecimal.ZERO)<=0)throw new IllegalArgumentException("Harga harus lebih dari 0.");
        if(stock<0)throw new IllegalArgumentException("Stok tidak boleh negatif.");
        String cat=category==null?"":category.trim();
        if(id==0)productRepository.insert(name.trim(),cat,price,stock);
        else productRepository.update(id,name.trim(),cat,price,stock);
    }
}
