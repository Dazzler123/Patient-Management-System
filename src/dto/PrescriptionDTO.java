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
public class PrescriptionDTO {
    String presID; //primary key
    LocalDate date;
    String time;
    String M_ID;
    String D_ID;
    String nic;
    String perDose;
    String timesADay;
    String noOfDays;
    String beforeOrAfterMeal; //select drug plan

}
