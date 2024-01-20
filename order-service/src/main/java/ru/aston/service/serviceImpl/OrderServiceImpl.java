package ru.aston.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.domain.dto.OrderDto;
import ru.aston.domain.entity.OrderEntity;
import ru.aston.domain.mapper.OrderMapper;
import ru.aston.domain.repository.OrderRepository;
import ru.aston.exceptions.BadParameters;
import ru.aston.exceptions.NotFoundException;
import ru.aston.service.serviceApi.OrderService;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final OrderMapper mapper;

    public OrderDto create(OrderDto orderDto) {
        OrderEntity orderEntity = mapper.fromDtoToEntity(orderDto);
        System.out.println(orderEntity);
        OrderDto orderDto1 = mapper.fromEntityToDto(repository.save(orderEntity));
        System.out.println(orderDto1);
        return orderDto1;
    }

    public OrderDto read(Long orderId) {
        if (orderId == null || orderId < 1) {
            throw new BadParameters("Неверный id");
        }
        return mapper.fromEntityToDto(repository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Заказ с таким id не найден")));
    }


    public OrderDto update(OrderDto orderDto) {
        if (orderDto.getId() == null) {
            throw new BadParameters("id не может быть null");
        }
        OrderEntity order = repository.findById(orderDto.getId())
                .orElseThrow(() -> new NotFoundException("Заказ с таким id не найден"));
        order.setCarId(orderDto.getCarId());
        order.setClientId(orderDto.getClientId());
        order.setDriverId(orderDto.getDriverId());
        order.setFrom(orderDto.getRoute().getFrom());
        order.setTo(orderDto.getRoute().getTo());
        return mapper.fromEntityToDto(repository.save(order));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new NotFoundException("Заказ с таким id не найден");
        }
       repository.deleteById(id);
    }
}
