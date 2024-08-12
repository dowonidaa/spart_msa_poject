package com.sparta.msa_exam.order.application.mapper;

import com.sparta.msa_exam.order.application.dto.OrderDto;
import com.sparta.msa_exam.order.domain.Order;
import com.sparta.msa_exam.order.domain.OrderItem;

import java.util.ArrayList;

public class OrderMapper {

    public static Order toEntityWithName(OrderDto dto) {
        return Order.builder()
                .name(dto.getName())
                .productIds(new ArrayList<>())
                .build();
    }


    public static OrderDto toDto(Order entity) {
        return OrderDto.builder()
                .orderId(entity.getOrderId())
                .name(entity.getName())
                .productIds(
                        entity.getProductIds()
                        .stream()
                        .map(OrderItem::getProductId)
                        .toList()
                )
                .build();
    }
}
