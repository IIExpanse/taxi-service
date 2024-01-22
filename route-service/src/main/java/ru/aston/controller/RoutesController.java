package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.dto.RouteDto;
import ru.aston.dto.RouteStatDto;
import ru.aston.service.RoutesService;

@RestController
@RequiredArgsConstructor
public class RoutesController {
    private final RoutesService service;

    @PutMapping("/routes")
    public RouteDto addRoute(@RequestBody RouteDto routeDto) {
        return service.addRoute(routeDto);
    }

    @GetMapping("/routes")
    public ResponseEntity<RouteStatDto> getRouteStat(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ) {
        if (from == null && to == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RouteStatDto routeStatDto = service.getRouteStat(from, to);
        return new ResponseEntity<>(routeStatDto, HttpStatus.OK);
    }
}
