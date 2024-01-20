package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.RouteDto;
import ru.aston.dto.RouteStatDto;
import ru.aston.entity.Route;
import ru.aston.mapper.RouteMapper;
import ru.aston.repository.RoutesRepository;

@Service
@RequiredArgsConstructor
public class RoutesService {
    private final RoutesRepository repository;
    private final RouteMapper mapper;

    public RouteDto addRoute(RouteDto routeDto) {
        Route route = mapper.fromDto(routeDto);
        route = repository.save(route);
        return mapper.toDto(route);
    }

    public RouteStatDto getRouteStat(String from, String to) {
        int stat = 0;

        if (to == null) {
            stat = repository.countRoutesByFrom(from);
        } else if (from == null) {
            stat = repository.countRoutesByTo(to);
        } else {
            stat = repository.countRoutesByFromAndTo(from, to);
        }
        return new RouteStatDto(from, to, stat);
    }
}
