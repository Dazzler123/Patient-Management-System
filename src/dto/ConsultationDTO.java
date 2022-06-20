package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ConsultationDTO {
    String consultID;
    LocalDate date;
    String time;
    String nic;
    String description;
    String D_ID;
}
