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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductClient productClient;

    @Transactional
    public OrderDto create(OrderDto dto) {
        Order order = orderRepository.save(OrderMapper.toEntityWithName(dto));
        List<Long> productIds = existProductIds(dto);

        if (productIds.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        createOrderItems(productIds, order);

        return OrderMapper.toDto(order);
    }

    @Cacheable(cacheNames = "orderCache", key = "args[0]")
    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OrderMapper.toDto(order);
    }

    @Transactional
    public OrderDto update(Long orderId, Long productId) {
        ProductDto product = productClient.getProduct(productId);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));
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
                .map(ProductDto::getProductId)
                .filter(productIds::contains)
                .toList();
    }
}
