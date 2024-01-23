package ru.aston.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.web.HttpClient;
import ru.aston.util.ServicesApis;

@RestController
@RequestMapping(value = "/reg")
@RequiredArgsConstructor
public class ExternalAuthService {
    private final HttpClient client;
    @Value("${auth.service.url}")
    private String url;

    public ResponseEntity<Object> register(Object object) {
        return client.post(url + ServicesApis.REG, object);
    }

    public ResponseEntity<Object> authenticate(Object object) {
        return client.post(url + ServicesApis.AUTH, object);
    }
}
