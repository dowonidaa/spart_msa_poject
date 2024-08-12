package com.sparta.msa_exam.order.application;

import com.sparta.msa_exam.order.application.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    ProductDto getProduct(Long productId);
}
