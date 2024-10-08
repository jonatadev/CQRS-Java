package br.com.beautique.services.impl;

import br.com.beautique.dtos.AppointmentDTO;
import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.dtos.CustomerDTO;
import br.com.beautique.dtos.FullAppointmentDTO;
import br.com.beautique.entities.AppointmentsEntity;
import br.com.beautique.entities.BeautyProceduresEntity;
import br.com.beautique.entities.CustomerEntity;
import br.com.beautique.repositories.AppointmentRepository;
import br.com.beautique.repositories.BeautyProcedureRepository;
import br.com.beautique.repositories.CustomerRepository;
import br.com.beautique.services.AppointmentsService;
import br.com.beautique.services.BrokerService;
import br.com.beautique.utils.ConvertUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private BrokerService brokerService;

    private final ConvertUtil<AppointmentsEntity, AppointmentDTO> convertUtil = new
            ConvertUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        var appointmentsEntity = convertUtil.convertToSource(appointmentDTO);
        var newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentToQueue(newAppointmentsEntity);
        return convertUtil.convertToTargert(newAppointmentsEntity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Optional<AppointmentsEntity> currentAppointment = appointmentRepository.findById(appointmentDTO.getId());
        if(currentAppointment.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }
        var appointmentsEntity = convertUtil.convertToSource(appointmentDTO);
        appointmentsEntity.setUpdatedAt(currentAppointment.get().getUpdatedAt());
        var updateAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentToQueue(updateAppointmentEntity);
        return convertUtil.convertToTargert(updateAppointmentEntity);
    }

    private void sendAppointmentToQueue(AppointmentsEntity appointmentsEntity) {
        CustomerDTO customerDto = appointmentsEntity.getCustomer() != null ?
                modelMapper.map(appointmentsEntity.getCustomer(), CustomerDTO.class) : null;
        BeautyProcedureDTO beautyProcedureDTO = appointmentsEntity.getBeautyProcedure() != null ?
                modelMapper.map(appointmentsEntity.getBeautyProcedure(), BeautyProcedureDTO.class) : null;

        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(customerDto)
                .beautyProcedure(beautyProcedureDTO)
                .build();
        brokerService.send("appointments", fullAppointmentDTO);
    }

    @Override
    public void deleteById(Long id) {
         AppointmentsEntity appointmentsEntity = appointmentRepository.findById(id)
                 .orElseThrow(
                         () -> new RuntimeException("Appointment not found")
                 );
         appointmentRepository.delete(appointmentsEntity);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentsDto) {
        CustomerEntity customerEntity = findCustomerById(appointmentsDto.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(appointmentsDto.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findAppointmentById(appointmentsDto.getId());
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedure(beautyProceduresEntity);
        appointmentsEntity.setAppointmentsOpen(false);

        AppointmentsEntity updateAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentToQueue(updateAppointmentEntity);
        return buildAppointmentsDTO(updateAppointmentEntity);
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity appointmentsEntity) {
        return AppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedure().getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .build();
    }

    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Appointment not found")
        );
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Appointment not found")
        );
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
    }
}
