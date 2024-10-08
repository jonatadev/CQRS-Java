package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.FullAppointmentDTO;
import br.com.ms_beautique_query.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8084")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
// http://localhost:8084/ms-beautique-query/appointment/customer/
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointments() {
        return ResponseEntity.ok(appointmentService.listAllAppointments());
    }
// http://localhost:8085/ms-beautique-query/appointment/customer/
    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointmentsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(appointmentService.listAllAppointmentsByCustomer(customerId));
    }
    // http://localhost:8085/ms-beautique-query/appointment/beauty-procedure/
    @GetMapping("/beauty-procedure/{beautyProcedureId}")
    ResponseEntity<List<FullAppointmentDTO>> listAllAppointmentsByBeautyProcedure(@PathVariable Long beautyProcedureId) {
        return ResponseEntity.ok(appointmentService.listAllAppointmentsBeautyProcedure(beautyProcedureId));
    }
}
