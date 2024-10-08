package br.com.beautique.services.impl;

import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.entities.BeautyProceduresEntity;
import br.com.beautique.repositories.BeautyProcedureRepository;
import br.com.beautique.services.BeautyProcedureService;
import br.com.beautique.services.BrokerService;
import br.com.beautique.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private BrokerService brokerService;

    private final ConvertUtil<BeautyProceduresEntity, BeautyProcedureDTO> convertUtil = new
    ConvertUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = convertUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProceduresEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProceduresToQueue(newBeautyProceduresEntity);
        return convertUtil.convertToTargert(newBeautyProceduresEntity);
    }

    @Override
    public void delete(long id) {
        Optional<BeautyProceduresEntity> beautyProceduresEntity = beautyProcedureRepository.findById(id);
        if(beautyProceduresEntity.isEmpty()) {
            throw new RuntimeException("Beauty Procedure not found");
        }
        beautyProcedureRepository.deleteById(id);
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO) {
        Optional<BeautyProceduresEntity> beautyProceduresEntityOptional = beautyProcedureRepository.findById(beautyProcedureDTO.getId());
        if(beautyProceduresEntityOptional.isEmpty()) {
            throw new RuntimeException("Beauty Procedure no found");

        }
        BeautyProceduresEntity beautyProceduresEntity = convertUtil.convertToSource(beautyProcedureDTO);
        beautyProceduresEntity.setAppointments(beautyProceduresEntityOptional.get().getAppointments());
        beautyProceduresEntity.setCreatedAt(beautyProceduresEntityOptional.get().getCreatedAt());
        BeautyProceduresEntity updateBeautyProcedureEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProceduresToQueue(beautyProceduresEntity);
        return convertUtil.convertToTargert(updateBeautyProcedureEntity);
    }

    private void sendBeautyProceduresToQueue(BeautyProceduresEntity beautyProceduresEntity) {
        BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO
                .builder().id(beautyProceduresEntity.getId())
                .name(beautyProceduresEntity.getName())
                .description(beautyProceduresEntity.getDescription())
                .price(beautyProceduresEntity.getPrice())
                .build();
        //       void send(String type, Object message);
        brokerService.send("beautyProcedures", beautyProcedureDTO);
    }
}
