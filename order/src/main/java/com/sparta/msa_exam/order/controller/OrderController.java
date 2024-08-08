package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderDto;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto create(@RequestBody OrderDto dto, HttpServletResponse response) {
        return orderService.create(dto);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId, HttpServletResponse response) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public OrderDto update(@PathVariable("orderId") Long orderId, Long productId, HttpServletResponse response) {
        return orderService.update(orderId, productId);
    }
}
