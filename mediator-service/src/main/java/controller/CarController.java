package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @PostMapping("/cars")
    public ResponseEntity<Object> addCar(@RequestBody Object object) {

    }

    @PutMapping("/cars")
    public ResponseEntity<Object> updateCar(@RequestBody Object object) {

    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Object> getCar(@PathVariable Integer id) {

    }

    @DeleteMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable Integer id) {

    }
}
