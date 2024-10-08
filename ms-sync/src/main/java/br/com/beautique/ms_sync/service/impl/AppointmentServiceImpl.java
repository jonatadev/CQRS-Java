package br.com.beautique.ms_sync.service.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.repositories.AppointmentRepository;
import br.com.beautique.ms_sync.service.AppointmentService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAppointment(FullAppointmentsDTO appointment) {
        try {
            SyncLogger.info("Saving customer: " + appointment.getId());
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            SyncLogger.error("error updating appointment customer: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            Query query = new Query(Criteria.where("customer.id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, FullAppointmentsDTO.class);
        } catch (Exception e) {
            SyncLogger.error("Error updating appointment customer: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentProcedures(BeautyProcedureDTO beautyProcedure) {
        try {
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedure.getId());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedure.getId()));
            Update update = new Update()
                    .set("beautProcedure.name", beautyProcedure.getName())
                    .set("beautyProcedure.description", beautyProcedure.getDescription());
            mongoTemplate.updateMulti(query, update, FullAppointmentsDTO.class);
        } catch (Exception e) {
            SyncLogger.error("error updating appointment beauty procedure: " + Arrays.toString(e.getStackTrace()));
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
