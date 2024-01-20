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
public class ClientController {
    @PostMapping("/clients")
    public ResponseEntity<Object> addClient(@RequestBody Object object) {

    }

    @PutMapping("/clients")
    public ResponseEntity<Object> updateClient(@RequestBody Object object) {

    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getClient(@PathVariable Integer id) {

    }

    @DeleteMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Integer id) {

    }
}
