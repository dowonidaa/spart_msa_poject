package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.OrderDto;
import com.sparta.msa_exam.order.dto.ProductDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;
import com.sparta.msa_exam.order.mapper.OrderMapper;
import com.sparta.msa_exam.order.repo.OrderItemRepository;
import com.sparta.msa_exam.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderDto create(OrderDto dto) {
        Order order = orderRepository.save(OrderMapper.toEntityWithName(dto));
        createOrderItems(existProductIds(dto), order);
        return OrderMapper.toDto(order);
    }


    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("주문 아이디가 올바르지 않습니다. : " + orderId));
        return OrderMapper.toDto(order);
    }

    @Transactional
    public OrderDto update(Long orderId, Long productId) {
        ProductDto product = productClient.getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("상품 아이디가 올바르지 않습니다 : " + productId);
        }
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("주문 아이디가 올바르지 않습니다. : " + orderId));
        OrderItem orderItem = OrderItem.builder().productId(productId).order(order).build();
        orderItemRepository.save(orderItem);
        order.saveOrderItem(orderItem);

        return OrderMapper.toDto(order);
    }








    private void createOrderItems(List<Long> productIds, Order savedOrder) {
        for (Long productId : productIds) {
            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .productId(productId)
                    .build();

            OrderItem save = orderItemRepository.save(orderItem);
            savedOrder.saveOrderItem(save);
        }
    }


    private List<Long> existProductIds(OrderDto dto) {
        List<ProductDto> products = productClient.getProducts();
        List<Long> productIds = dto.getProductIds();

        return products.stream()
                .map(ProductDto::getProductId).filter(productIds::contains)
                .toList();
    }
}
