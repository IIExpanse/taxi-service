package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderController {
    @PostMapping("/orders")
    public ResponseEntity<Object> addOrder(
            @RequestBody Object object,
            @RequestParam Integer client,
            @RequestParam Integer driver,
            @RequestParam Integer car) {

    }

    @PutMapping("/orders")
    public ResponseEntity<Object> updateOrder(
            @RequestBody Object object,
            @RequestParam Integer client,
            @RequestParam Integer driver,
            @RequestParam Integer car) {

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Integer id) {

    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Integer id) {

    }
}
