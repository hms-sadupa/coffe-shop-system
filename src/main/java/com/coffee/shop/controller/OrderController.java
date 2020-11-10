package com.coffee.shop.controller;

import com.coffee.shop.dto.ApiResponse;
import com.coffee.shop.dto.FailApiResponse;
import com.coffee.shop.dto.OrderApiRequest;
import com.coffee.shop.dto.SuccessApiResponse;
import com.coffee.shop.model.CoffeeOrder;
import com.coffee.shop.service.OrderService;
import com.coffee.shop.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable Long id) {
        try {
            CoffeeOrder order = orderService.getCoffeeOrder(id);
            return ResponseEntity.ok(new SuccessApiResponse(order));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new FailApiResponse(StatusCode.E100, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderApiRequest orderApiRequest) {
        try {
            CoffeeOrder order = orderService.createOrder(orderApiRequest);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new FailApiResponse(StatusCode.E100, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> cancelOrder(@PathVariable long id) {
        try {
            orderService.cancelOrder(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new FailApiResponse(StatusCode.E100, e.getMessage()));
        }
    }
}
