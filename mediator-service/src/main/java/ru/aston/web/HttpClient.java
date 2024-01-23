package ru.aston.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpClient {
    private final RestTemplate rest = new RestTemplate();

    public ResponseEntity<Object> post(String uri, Object body) {
        return rest.exchange(uri, HttpMethod.POST, new HttpEntity<>(body), Object.class);
    }

    public ResponseEntity<Object> getById(String uri, String id) {
        return rest.exchange(uri + "/" + id, HttpMethod.GET, HttpEntity.EMPTY, Object.class);
    }

    public ResponseEntity<Object> getWithParams(String uri, Map<String, String> params) {
        StringBuilder query = new StringBuilder("?");
        params.forEach((key, value) -> query
                .append(key)
                .append("=")
                .append(value)
                .append("&"));
        query.delete(query.length() - 1, query.length());

        return rest.exchange(uri + query, HttpMethod.GET, HttpEntity.EMPTY, Object.class);
    }

    public ResponseEntity<Object> put(String uri, Object body) {
        return rest.exchange(uri, HttpMethod.PUT, new HttpEntity<>(body), Object.class);
    }

    public ResponseEntity<Object> delete(String uri, String id) {
        return rest.exchange(uri + "/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Object.class);
    }
}
