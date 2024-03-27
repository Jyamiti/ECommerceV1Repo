package org.example.ecommercev1.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.ecommercev1.Models.Category;
@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private  String description;
    private  String imageURL;
}
