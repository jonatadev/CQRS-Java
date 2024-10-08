package br.com.beautique.controllers;

import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PatchMapping()
    ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.update(customerDTO));
    }
}