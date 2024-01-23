package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.aston.web.HttpClient;
import ru.aston.util.ServicesApis;

@Service
@RequiredArgsConstructor
public class ExternalCarService implements CRUDService {
    private final HttpClient client;
    @Value("${car.service.url}")
    private String url;

    @Override
    public ResponseEntity<Object> post(Object object) {
        return client.post(url + ServicesApis.CAR, object);
    }

    @Override
    public ResponseEntity<Object> get(String id) {
        return client.getById(url + ServicesApis.CAR, id);
    }

    @Override
    public ResponseEntity<Object> put(Object object) {
        return client.put(url + ServicesApis.CAR, object);
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        return client.delete(url + ServicesApis.CAR, id);
    }
}
