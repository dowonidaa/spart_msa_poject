package com.sparta.msa_exam.order.domain.repo;

import com.sparta.msa_exam.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
