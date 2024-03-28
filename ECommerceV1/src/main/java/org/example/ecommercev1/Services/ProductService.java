package org.example.ecommercev1.Services;

import org.example.ecommercev1.Models.Product;

import java.util.List;

public interface ProductService {
     Product getSingleProduct(Long id);

     List<Product> getAllProducts();

     Product replaceProduct(Long id, Product p);

     Product addProduct(Product p);
}
