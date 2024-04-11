package org.example.ecommercev1.Services;

import org.example.ecommercev1.Models.Category;
import org.example.ecommercev1.Models.Product;
import org.example.ecommercev1.Repositories.CategoryRepository;
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
    CategoryRepository categoryRepository;
    @Autowired
    SelfStoreProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> p = productRepository.findById(id);
        if(p.isEmpty() || p.get().isDeleted()) return null;
        return p.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByIsDeleted(false);
    }

    @Override
    public Product replaceProduct(Long id, Product p) {
        Product actual_prod = getSingleProduct(id);

        //Product Not found
        if(actual_prod == null) return null;

        //update product attributes
        actual_prod.setTitle(p.getTitle());
        actual_prod.setImageURL(p.getImageURL());
        actual_prod.setPrice(p.getPrice());
        actual_prod.setDescription(p.getDescription());
        actual_prod.setDeleted(false);

        //fetch and update category
        Optional<Category> c = categoryRepository.getByName(p.getCategory().getName());
        if(c.isEmpty()) actual_prod.setCategory(categoryRepository.save(p.getCategory()));
        else actual_prod.setCategory(c.get());

        //save updated product
        return productRepository.save(actual_prod);
    }

    public Product addProduct(Product p){
        //fetch or add category
        Optional<Category> c = categoryRepository.getByName(p.getCategory().getName());
        if(c.isEmpty()) p.setCategory(categoryRepository.save(p.getCategory()));
        else p.setCategory(c.get());

        //add category
        return productRepository.save(p);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product p = getSingleProduct(id);
        if(p == null) return null;
        p.setDeleted(true);
        productRepository.save(p);
        return p;
    }
}
