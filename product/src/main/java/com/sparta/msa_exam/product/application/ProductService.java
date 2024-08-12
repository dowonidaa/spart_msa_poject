package com.sparta.msa_exam.product.application;

import com.sparta.msa_exam.product.application.dto.ProductDto;
import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.application.mapper.ProductMapper;
import com.sparta.msa_exam.product.domain.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @CacheEvict(cacheNames = "productAllCache",allEntries = true)
    public ProductDto create(ProductDto dto) {
        Product product = productRepository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDto(product);
    }

    @Cacheable(cacheNames = "productAllCache", key = "methodName")
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
