package br.com.beautique.services.impl;

import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.repositories.CustomerRepository;
import br.com.beautique.services.BrokerService;
import br.com.beautique.services.CustomerService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConvertUtil<CustomerEntity, CustomerDTO>
            convertUtil = new ConvertUtil<>(CustomerEntity.class, CustomerDTO.class);

    @Override
    public CustomerDTO create(CustomerDTO customerDto) {

        var customerEntity = convertUtil.convertToSource(customerDto);
        var newCustomerEntity = customerRepository.save(customerEntity);
        sendCustomerToQueue(newCustomerEntity);
        return convertUtil.convertToTargert(newCustomerEntity);

//        var customerEntity = CustomerEntity.builder()
//                .name(customerDto.getName())
//                .email(customerDto.getEmail())
//                .phone(customerDto.getPhone())
//                .build();
//
//        var newCustomerEntity = customerRepository.save(customerEntity);
//
//        return CustomerDTO.builder()
//                .id(newCustomerEntity.getId())
//                .name(newCustomerEntity.getName())
//                .email(newCustomerEntity.getEmail())
//                .phone(newCustomerEntity.getPhone())
//                .build();
    }

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> customerEntityOptional =
                customerRepository.findById(id);
        if(customerEntityOptional.isEmpty()) {
            throw new RuntimeException("Customer not found!");
        }
        customerRepository.delete(customerEntityOptional.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {

        Optional<CustomerEntity> customerEntityOptional =
                customerRepository.findById(customerDTO.getId());
        if(customerEntityOptional.isEmpty()) {
            throw new RuntimeException("Customer not found!");
        }

        CustomerEntity customerEntity = convertUtil.convertToSource(customerDTO);
        customerEntity.setAppointments(customerEntityOptional.get().getAppointments());
        customerEntity.setCreatedAt(customerEntityOptional.get().getCreatedAt());

        CustomerDTO updateCustomerDTO = convertUtil.convertToTargert(customerRepository.save(customerEntity));
        sendCustomerToQueue(customerEntity);

        return updateCustomerDTO;
    }

    private void sendCustomerToQueue(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .build();
        brokerService.send("customer", customerDTO);
    }
}

/*
Retorno sem usar DTO, apos salvar usuário.

{
    "id": 1,
    "createdAt": "2024-09-22T15:31:06.137839",
    "updatedAt": "2024-09-22T15:31:06.137839",
    "name": "Jonatas Alves da Silva",
    "email": "jonatadev@gmail.com",
    "phone": "(44)998271089"
}
 */