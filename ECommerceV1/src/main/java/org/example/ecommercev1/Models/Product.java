package org.example.ecommercev1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseEntity {
    private String title;
    private double price;
    @ManyToOne
    private Category  category;
    private  String description;
    private  String imageURL;
    private boolean isDeleted;
}
