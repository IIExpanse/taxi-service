package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.aston.web.HttpClient;
import ru.aston.util.ServicesApis;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExternalRouteService {
    private final HttpClient client;
    @Value("${route.service.url}")
    private String url;

    public ResponseEntity<Object> put(Object object) {
        return client.put(url + ServicesApis.ROUTE, object);
    }


    public ResponseEntity<Object> get(Map<String, String> params) {
        return client.getWithParams(url + ServicesApis.ROUTE, params);
    }

}
