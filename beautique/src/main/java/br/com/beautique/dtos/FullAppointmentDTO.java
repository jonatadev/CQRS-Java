package br.com.beautique.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullAppointmentDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}
