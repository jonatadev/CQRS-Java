package br.com.beautique.ms_sync.dtos.appointments;

import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "appointments") // mongoDb
public class FullAppointmentsDTO {

    private Long id;
    private LocalDate dateTime;
    private Boolean appointmentsOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}