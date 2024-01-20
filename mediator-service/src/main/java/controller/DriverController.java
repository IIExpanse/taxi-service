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
public class DriverController {
    @PostMapping("/drivers")
    public ResponseEntity<Object> addDriver(@RequestBody Object object) {

    }

    @PutMapping("/drivers")
    public ResponseEntity<Object> updateDriver(@RequestBody Object object) {

    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<Object> getDriver(@PathVariable Integer id) {

    }

    @DeleteMapping("/drivers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDriver(@PathVariable Integer id) {

    }
}
