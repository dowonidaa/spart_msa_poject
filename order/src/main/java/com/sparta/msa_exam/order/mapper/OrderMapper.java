package com.sparta.msa_exam.order.mapper;

import com.sparta.msa_exam.order.dto.OrderDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

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
