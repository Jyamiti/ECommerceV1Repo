package org.example.ecommercev1.Repositories;

import org.example.ecommercev1.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> getByName(String name);
}
