package org.example.ecommercev1.Services;

import org.example.ecommercev1.Models.Product;
import org.example.ecommercev1.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component("selfStoreProductService")
@Service

public class SelfStoreProductService implements ProductService {
    ProductRepository productRepository;
    @Autowired
    SelfStoreProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty()) return null;
        return p.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product p) {
        return null;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}
