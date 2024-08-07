package com.sparta.msa_exam.product.mapper;

import com.sparta.msa_exam.product.dto.ProductDto;
import com.sparta.msa_exam.product.entity.Product;

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
