package ru.aston.domain.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import ru.aston.domain.dto.OrderDto;
import ru.aston.domain.entity.OrderEntity;

@Mapper(componentModel = "Spring")
public interface OrderMapper {
    @Mapping(source = "route.from", target = "from")
    @Mapping(source = "route.to", target = "to")
    OrderEntity fromDtoToEntity(OrderDto dto);

    @Mapping(source = "from", target = "route.from")
    @Mapping(source = "to", target = "route.to")
    OrderDto fromEntityToDto(OrderEntity entity);
}
