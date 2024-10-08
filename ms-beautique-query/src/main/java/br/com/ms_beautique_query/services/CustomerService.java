package br.com.ms_beautique_query.services;

import br.com.ms_beautique_query.dtos.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerDTO> listAllCustomers();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);
}
