package com.sparta.msa_exam.order.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long orderId;
    private String name;
    private List<Long> productIds;

}
