package ru.aston.mapper;

import org.mapstruct.Mapper;
import ru.aston.dto.RouteDto;
import ru.aston.entity.Route;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDto toDto(Route route);
    Route fromDto(RouteDto routeDto);
}
