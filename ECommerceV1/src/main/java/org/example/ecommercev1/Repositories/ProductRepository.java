package org.example.ecommercev1.Repositories;

import org.example.ecommercev1.Models.Category;
import org.example.ecommercev1.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);

    @Override
    Optional<Product> findById(Long aLong);
    List<Product> findAllByIsDeleted(Boolean b);
}
