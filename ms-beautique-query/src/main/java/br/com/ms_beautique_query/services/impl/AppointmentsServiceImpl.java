package br.com.ms_beautique_query.services.impl;

import br.com.ms_beautique_query.dtos.FullAppointmentDTO;
import br.com.ms_beautique_query.repositories.AppointmentRepository;
import br.com.ms_beautique_query.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<FullAppointmentDTO> listAllAppointments() {
        try {
            return appointmentRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException("Error listing all appointments");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentsByCustomer(Long customerId) {
        try {
            return appointmentRepository.listAppointmentsByCustomerId(customerId);
        }catch (Exception e) {
            throw new RuntimeException("Error listing all appointments by customer");
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllAppointmentsBeautyProcedure(Long beautyProcedureId) {
        try {
            return appointmentRepository.listAppointmentsByBeautyProcedureId(beautyProcedureId);
        }catch (Exception e) {
            throw new RuntimeException("Error listing all appointments by beauty procedure");
        }
    }
}
