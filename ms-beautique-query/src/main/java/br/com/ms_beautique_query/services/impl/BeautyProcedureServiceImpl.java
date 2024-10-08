package br.com.ms_beautique_query.services.impl;

import br.com.ms_beautique_query.dtos.BeautyProcedureDTO;
import br.com.ms_beautique_query.repositories.BeautyProcedureRepository;
import br.com.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedures() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedures");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try {
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedures by name");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description) {
        try {
            return beautyProcedureRepository.findByEmailLikeIgnoreCase(description);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all beauty procedures by description");
        }
    }
}
