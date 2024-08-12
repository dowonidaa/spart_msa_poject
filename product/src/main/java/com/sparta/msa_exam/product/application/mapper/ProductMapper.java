package com.sparta.msa_exam.product.application.mapper;

import com.sparta.msa_exam.product.application.dto.ProductDto;
import com.sparta.msa_exam.product.domain.Product;

public class ProductMapper {

    public static Product toEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .supplyPrice(dto.getSupplyPrice())
                .build();
    }

    public static ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .supplyPrice(entity.getSupplyPrice())
                .build();
    }
}
