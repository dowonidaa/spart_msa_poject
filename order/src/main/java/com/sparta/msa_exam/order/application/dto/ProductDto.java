package com.sparta.msa_exam.order.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {

    private Long productId;
    private String name;
    private Integer supplyPrice;

}
