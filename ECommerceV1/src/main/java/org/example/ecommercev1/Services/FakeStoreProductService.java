package org.example.ecommercev1.Services;

import org.example.ecommercev1.Dtos.FakeStoreProductDto;
import org.example.ecommercev1.Models.Category;
import org.example.ecommercev1.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    @Autowired
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertDtoProduct(FakeStoreProductDto dto){
        Product p = new Product();
        p.setId(dto.getId());
        p.setTitle(dto.getTitle());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());
        p.setImageURL(dto.getImageURL());
        p.setCategory(new Category());
        p.getCategory().setName(dto.getCategory());
        return p;
    }
    private FakeStoreProductDto convertProductDto(Product p){
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        dto.setPrice(p.getPrice());
        dto.setDescription(p.getDescription());
        dto.setImageURL(p.getImageURL());
        dto.setCategory(p.getCategory().getName());
        return dto;
    }
    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto fakeStoreProductDto =    restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return convertDtoProduct(fakeStoreProductDto);
    }

    public List<Product> getAllProducts(){
        FakeStoreProductDto[] dtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : dtos){
            products.add(convertDtoProduct(dto));
        }
        return products;
    }
    public Product replaceProduct(Long id, Product product){
//        restTemplate.put();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(convertProductDto(product));
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto dto =  restTemplate.execute("https://fakestoreapi.com/products/" +id, HttpMethod.PUT, requestCallback, responseExtractor);
        return(convertDtoProduct(dto));
//        RequestCallback requestCallback1 = restTemplate.httpEntityCallback(Product.class);
//        execute(url, HttpMethod.PUT, requestCallback, null);
    }

    @Override
    public Product addProduct(Product p) {
        return null;
    }

}
