package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.CustomerDTO;
import br.com.ms_beautique_query.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping() // http://localhost:8084/ms-beautique-query/customer
    ResponseEntity<List<CustomerDTO>> listAllCustomers() {
        return ResponseEntity.ok(customerService.listAllCustomers());
    }

    @GetMapping("/name/{name}")
    ResponseEntity<List<CustomerDTO>> listByNameLikeIgnoreCase(@PathVariable String name) {
        return ResponseEntity.ok(customerService.listByNameLikeIgnoreCase(name));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<List<CustomerDTO>> listByEmailLikeIgnoreCase(@PathVariable String email) {
        return ResponseEntity.ok(customerService.listByEmailLikeIgnoreCase(email));
    }
}
