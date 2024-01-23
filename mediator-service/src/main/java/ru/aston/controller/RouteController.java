package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.service.ExternalRouteService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {
    private final ExternalRouteService service;

    @PutMapping
    public ResponseEntity<Object> addRoute(@RequestBody Object object) {
        ResponseEntity<Object> response = service.put(object);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping
    public ResponseEntity<Object> getRoute(@RequestParam(required = false) String from,
                                           @RequestParam(required = false) String to) {
        Map<String, String> map = new HashMap<>();
        if (from != null) {
            map.put("from", from);
        }
        if (to != null) {
            map.put("to", to);
        }
        ResponseEntity<Object> response = service.get(map);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
