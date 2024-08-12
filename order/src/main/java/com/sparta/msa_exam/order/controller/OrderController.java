package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.application.dto.OrderDto;
import com.sparta.msa_exam.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto create(@RequestBody OrderDto dto) {
        return orderService.create(dto);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderDto update(@PathVariable("orderId") Long orderId,@RequestParam Long productId) {
        return orderService.update(orderId, productId);
    }
}
