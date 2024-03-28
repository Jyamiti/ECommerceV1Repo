package org.example.ecommercev1.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {
    private String name;
}
