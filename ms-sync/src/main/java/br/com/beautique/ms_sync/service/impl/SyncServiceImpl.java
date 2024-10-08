package br.com.beautique.ms_sync.service.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.repositories.CustomerRepository;
import br.com.beautique.ms_sync.service.AppointmentService;
import br.com.beautique.ms_sync.service.BeautyProcedureService;
import br.com.beautique.ms_sync.service.CustomerService;
import br.com.beautique.ms_sync.service.SyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @Override
    public void syncCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer: " + customer.getId());
            customerService.saveCustomer(customer);
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            appointmentService.updateAppointmentCustomer(customer);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentsDTO appointment) {
        try {
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentService.saveAppointment(appointment);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProcedure) {
        try {
            SyncLogger.info("Saving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureService.saveBeautyProcedure(beautyProcedure);
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedure.getId());
            appointmentService.updateAppointmentProcedures(beautyProcedure);
        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

}
