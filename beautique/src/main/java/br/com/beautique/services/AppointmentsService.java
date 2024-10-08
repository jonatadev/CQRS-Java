package br.com.beautique.services;

import br.com.beautique.dtos.AppointmentDTO;

public interface AppointmentsService {
    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO update(AppointmentDTO appointmentDTO);
    void deleteById(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentsDto);
}
