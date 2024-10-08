package br.com.beautique.ms_sync.service.impl;

import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.repositories.BeautyProcedureRepository;
import br.com.beautique.ms_sync.service.BeautyProcedureService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public void saveBeautyProcedure(BeautyProcedureDTO beautyProcedure) {
        try {
            SyncLogger.info("Saving customer: " + beautyProcedure.getId());
            beautyProcedureRepository.save(beautyProcedure);
        } catch (Exception e) {
            SyncLogger.error("error saving beauty procedure: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
