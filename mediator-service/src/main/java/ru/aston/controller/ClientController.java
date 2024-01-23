package ru.aston.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.service.ExternalClientService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ExternalClientService service;

    @PostMapping
    public ResponseEntity<Object> addClient(@RequestBody Object object) {
        ResponseEntity<Object> response = service.post(object);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable String id) {
        ResponseEntity<Object> response = service.get(id);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping
    public ResponseEntity<Object> updateClient(@RequestBody Object object) {
        ResponseEntity<Object> response = service.put(object);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable String id) {
        ResponseEntity<Object> response = service.delete(id);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
}
