package br.com.beautique.ms_sync.listeners.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.listeners.ListenerConfig;
import br.com.beautique.ms_sync.service.SyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@AllArgsConstructor
public class RabbitMQListenerConfig implements ListenerConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SyncService syncService;

    @RabbitListener(queues = "customerQueue")
    @Override
    public void listenToCustomerQueue(String message) {
        try {
            var customer = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customer);
            SyncLogger.info("Message received from queue customerQueue" + customer.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen customer queue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "appointmentQueue")
    @Override
    public void listenToAppointmentQueue(String message) {
        try {
            var appointments = objectMapper.readValue(message, FullAppointmentsDTO.class);
            syncService.syncAppointment(appointments);
            SyncLogger.info("Message received from queue customerQueue" + appointments.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen customer queue: " + e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "beautyProcedureQueue")
    public void listenToBeautyProcedureQueue(String message) {
        try {
            var beautyProcedures = objectMapper.readValue(message, BeautyProcedureDTO.class);
            syncService.syncBeautyProcedures(beautyProcedures);
            SyncLogger.info("Message received from queue beautyProcedures" + beautyProcedures.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen beauty procedure queue: " + e.getMessage());
        }
    }
}
