package br.com.beautique.ms_sync.service;

import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface CustomerService {
    void saveCustomer(CustomerDTO customer);
}
