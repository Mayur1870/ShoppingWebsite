package com.shopping.controller;

import com.shopping.entity.Products;
import com.shopping.repo.ProductRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepo productRepo;

    // Constructor injection of the ProductRepo
    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Get all products
    @GetMapping("/get")
    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    // Get product by ID
    @GetMapping("/get/{id}")
    public Products getProductById(@PathVariable Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    // Custom method to get products by price greater than a specified amount
    @GetMapping("/getByPrice/{price}")
    public List<Products> getProductsByPrice(@PathVariable double price) {
        return productRepo.findByProductPriceGreaterThan(price);
    }

    // Custom method to get products in stock
    @GetMapping("/inStock")
    public List<Products> getProductsInStock() {
        return productRepo.findByProductInStock(true);
    }

    @GetMapping("/byQuantity")
    public List<Products> getProductsByQuantity(@RequestParam int quantity) {
        return productRepo.findByProductQuantityGreaterThan(quantity);
    }

    // Create products
    @PostMapping("/post")
    public String createProducts(@RequestBody List<Products> products) {
        productRepo.saveAll(products);
        return "Products created successfully!";
    }

    // Update a product
    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, @RequestBody Products productDetails) {
        Optional<Products> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setProductName(productDetails.getProductName());
            product.setProductDescription(productDetails.getProductDescription());
            product.setProductPrice(productDetails.getProductPrice());
            product.setProductInStock(productDetails.isProductInStock());
            product.setProductQuantity(productDetails.getProductQuantity());
            productRepo.save(product);
            return "Product updated successfully!";
        } else {
            return "Product not found!";
        }
    }

    // Delete a product
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product deleted successfully!";
        } else {
            return "Product not found!";
        }
    }

    // Delete all products
    @DeleteMapping("/deleteAll")
    public String deleteAllProducts() {
        long deletedCount = productRepo.count();
        productRepo.deleteAll();
        return deletedCount + " products deleted successfully!";
    }
}