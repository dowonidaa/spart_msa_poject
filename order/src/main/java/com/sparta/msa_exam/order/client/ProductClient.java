package com.sparta.msa_exam.order.client;

import com.sparta.msa_exam.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDto> getProducts();

    @GetMapping("/products/{productId}")
    ProductDto getProduct(@PathVariable("productId") Long productId);

}
