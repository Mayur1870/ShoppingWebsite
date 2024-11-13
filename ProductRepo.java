package com.shopping.repo;

import com.shopping.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products, Integer> {
    List<Products> findByProductPriceGreaterThan(double price);
    List<Products> findByProductInStock(boolean inStock);
    List<Products> findByProductQuantityGreaterThan(int productQuantity);

}