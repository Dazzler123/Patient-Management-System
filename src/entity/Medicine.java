package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Medicine {
    private String M_ID;  //primary key
    private String name;
    private String brand;
    private String country;
    private String volume;
    private LocalDate date_of_manufacture;
    private LocalDate date_of_expiry;
    private double p_p_unit;
    private int qty_on_hand;

}
