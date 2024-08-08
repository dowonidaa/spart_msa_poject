package com.sparta.msa_exam.product.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ProductDto implements Serializable {

    private Long productId;
    private String name;
    private Integer supplyPrice;

}
