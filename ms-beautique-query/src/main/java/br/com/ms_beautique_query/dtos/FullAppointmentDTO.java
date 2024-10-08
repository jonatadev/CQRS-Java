package br.com.ms_beautique_query.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class FullAppointmentDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;
    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}
