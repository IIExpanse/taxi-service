package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.aston.util.ServicesApis;
import ru.aston.web.HttpClient;

@Service
@RequiredArgsConstructor
public class ExternalClientService implements CRUDService {
    private final HttpClient client;
    @Value("${client.service.url}")
    private String url;

    @Override
    public ResponseEntity<Object> post(Object object) {
        return client.post(url + ServicesApis.CLIENT, object);
    }

    @Override
    public ResponseEntity<Object> get(String id) {
        return client.getById(url + ServicesApis.CLIENT, id);
    }

    @Override
    public ResponseEntity<Object> put(Object object) {
        return client.put(url + ServicesApis.CLIENT, object);
    }

    @Override
    public ResponseEntity<Object> delete(String id) {
        return client.delete(url + ServicesApis.CLIENT, id);
    }
}