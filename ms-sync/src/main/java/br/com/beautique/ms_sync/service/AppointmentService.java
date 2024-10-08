package br.com.beautique.ms_sync.service;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface AppointmentService {
    void saveAppointment(FullAppointmentsDTO appointment);
    void updateAppointmentCustomer(CustomerDTO customer);
    void updateAppointmentProcedures(BeautyProcedureDTO beautyProcedure);
}
