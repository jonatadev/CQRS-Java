package br.com.beautique.repositories;

import br.com.beautique.entities.BeautyProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProcedureRepository extends
        JpaRepository<BeautyProceduresEntity, Long> {
}
