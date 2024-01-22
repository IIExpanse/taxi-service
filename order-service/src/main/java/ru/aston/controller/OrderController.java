package ru.aston.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aston.domain.dto.OrderDto;
import ru.aston.service.serviceApi.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Validated
public class OrderController {
    private final OrderService service;

    @GetMapping("/{orderId}")
    public OrderDto read(@PathVariable Long orderId) {
        return service.read(orderId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderDto order) {
        return service.create(order);
    }

    @PutMapping
    public OrderDto update(@Valid @RequestBody OrderDto order) {
        return service.update(order);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable Long orderId) {
        service.delete(orderId);
    }
}
