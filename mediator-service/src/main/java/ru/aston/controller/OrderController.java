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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.service.ExternalCarService;
import ru.aston.service.ExternalClientService;
import ru.aston.service.ExternalDriverService;
import ru.aston.service.ExternalOrderService;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final ExternalClientService clientService;
    private final ExternalDriverService driverService;
    private final ExternalCarService carService;
    private final ExternalOrderService orderService;

    @PostMapping
    public ResponseEntity<Object> addOrder(
            @RequestBody Object object,
            @RequestParam String clientId,
            @RequestParam String driverId,
            @RequestParam String carId) {
        if (!linkedObjectsExist(clientId, driverId, carId)) {
            return new ResponseEntity<>("One or more linked objects not found", CONFLICT);
        }
        ResponseEntity<Object> response = orderService.post(object);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable String id) {
        ResponseEntity<Object> response = orderService.get(id);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @PutMapping
    public ResponseEntity<Object> updateOrder(
            @RequestBody Object object) {
        ResponseEntity<Object> response = orderService.put(object);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String id) {
        ResponseEntity<Object> response = orderService.delete(id);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    private boolean linkedObjectsExist(String clientId, String driverId, String carId) {
        try {
            return clientService.get(clientId).getStatusCode() == OK
                    && driverService.get(driverId).getStatusCode() == OK
                    && carService.get(carId).getStatusCode() == OK;

        } catch (Exception e) {
            return false;
        }

    }
}
