package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.mapper.ProductMapper;
import com.sparta.msa_exam.product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto create(ProductDto dto) {
        Product product = productRepository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDto(product);
    }


    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("상품 아이디가 올바르지 않습니다. : " + productId));

        return ProductMapper.toDto(product);
    }
}
