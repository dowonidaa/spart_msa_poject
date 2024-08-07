package com.sparta.msa_exam.order.mapper;

import com.sparta.msa_exam.order.dto.OrderDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;

public class OrderMapper {

    public static Order toEntityWithName(OrderDto dto) {
        return Order.builder()
                .name(dto.getName())
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
