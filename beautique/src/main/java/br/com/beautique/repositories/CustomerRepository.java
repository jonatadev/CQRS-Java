package br.com.beautique.repositories;

import br.com.beautique.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends
        JpaRepository<CustomerEntity, Long> {
}