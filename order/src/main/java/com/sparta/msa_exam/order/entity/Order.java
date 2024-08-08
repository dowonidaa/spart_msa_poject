package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String name;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> productIds = new ArrayList<>();

    public void saveOrderItem(OrderItem save) {
        productIds.add(save);
    }
}
