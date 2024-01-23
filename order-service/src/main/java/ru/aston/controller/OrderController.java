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

    @GetMapping("/{id}")
    public OrderDto read(@PathVariable Long id) {
        return service.read(id);
    }

    @PostMapping
    public OrderDto create(@Valid @RequestBody OrderDto order) {
        return service.create(order);
    }

    @PutMapping
    public OrderDto update(@Valid @RequestBody OrderDto order) {
        return service.update(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
