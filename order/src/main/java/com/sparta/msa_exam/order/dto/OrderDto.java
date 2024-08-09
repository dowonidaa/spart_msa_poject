package com.sparta.msa_exam.order.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {

    private Long orderId;
    private String name;
    private List<Long> productIds;

}
