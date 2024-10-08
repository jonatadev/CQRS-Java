package br.com.beautique.ms_sync.service;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface SyncService {
    void syncCustomer(CustomerDTO customer);
    void syncAppointment(FullAppointmentsDTO appointment);
    void syncBeautyProcedures(BeautyProcedureDTO beautyProcedure);
}
