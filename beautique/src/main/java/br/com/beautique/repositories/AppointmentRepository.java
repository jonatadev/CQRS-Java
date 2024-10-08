package br.com.beautique.repositories;

import br.com.beautique.entities.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentsEntity, Long> {

}
