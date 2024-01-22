package ru.aston.service.serviceApi;

import ru.aston.domain.dto.OrderDto;

public interface OrderService {
    OrderDto create(OrderDto orderDto);

    OrderDto read(Long orderId);

    OrderDto update(OrderDto orderDto);

    void delete(Long id);
}
