package ru.aston.service;

import org.springframework.http.ResponseEntity;

public interface CRUDService {
    ResponseEntity<Object> post(Object object);

    ResponseEntity<Object> get(String id);

    ResponseEntity<Object> put(Object object);

    ResponseEntity<Object> delete(String id);
}
