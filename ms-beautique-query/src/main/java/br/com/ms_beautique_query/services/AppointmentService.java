package br.com.ms_beautique_query.services;

import br.com.ms_beautique_query.dtos.FullAppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<FullAppointmentDTO> listAllAppointments();
    List<FullAppointmentDTO> listAllAppointmentsByCustomer(Long customerId);
    List<FullAppointmentDTO> listAllAppointmentsBeautyProcedure(Long beautyProcedureId);
}
