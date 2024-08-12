package com.sparta.msa_exam.order.infrastructure;

import com.sparta.msa_exam.order.application.ProductService;
import com.sparta.msa_exam.order.application.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient  extends ProductService {

    @GetMapping("/products")
    List<ProductDto> getProducts();

    @GetMapping("/products/{productId}")
    ProductDto getProduct(@PathVariable("productId") Long productId);

}
