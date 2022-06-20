package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Prescription_Cart {
    String id;
    String name;
    String per_dose;
    String times_a_day;
    int no_of_days;
    String drug_plan;
}
