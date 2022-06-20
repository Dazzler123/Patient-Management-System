package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicineDTO {
    private String M_ID; //primary key
    private String name;
    private String brand;
    private String country;
    private String volume;
    private LocalDate date_of_manufacture;
    private LocalDate date_of_expiry;
    private BigDecimal p_p_unit; //price per unit
    private int qty_on_hand;

    public MedicineDTO(String m_ID, String name, String brand, String country, String volume, LocalDate date_of_manufacture, LocalDate date_of_expiry, BigDecimal p_p_unit, int qty_on_hand) {
        M_ID = m_ID;
        this.name = name;
        this.brand = brand;
        this.country = country;
        this.volume = volume;
        this.date_of_manufacture = date_of_manufacture;
        this.date_of_expiry = date_of_expiry;
        this.p_p_unit = p_p_unit;
        this.qty_on_hand = qty_on_hand;
    }

    public MedicineDTO() {
    }

    public String getM_ID() {
        return M_ID;
    }

    public void setM_ID(String m_ID) {
        M_ID = m_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public LocalDate getDate_of_manufacture() {
        return date_of_manufacture;
    }

    public void setDate_of_manufacture(LocalDate date_of_manufacture) {
        this.date_of_manufacture = date_of_manufacture;
    }

    public LocalDate getDate_of_expiry() {
        return date_of_expiry;
    }

    public void setDate_of_expiry(LocalDate date_of_expiry) {
        this.date_of_expiry = date_of_expiry;
    }

    public BigDecimal getP_p_unit() {
        return p_p_unit;
    }

    public void setP_p_unit(BigDecimal p_p_unit) {
        this.p_p_unit = p_p_unit;
    }

    public int getQty_on_hand() {
        return qty_on_hand;
    }

    public void setQty_on_hand(int qty_on_hand) {
        this.qty_on_hand = qty_on_hand;
    }

    @Override
    public String toString() {
        return "MedicineDTO{" +
                "M_ID='" + M_ID + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", country='" + country + '\'' +
                ", volume='" + volume + '\'' +
                ", date_of_manufacture=" + date_of_manufacture +
                ", date_of_expiry=" + date_of_expiry +
                ", p_p_unit=" + p_p_unit +
                ", qty_on_hand=" + qty_on_hand +
                '}';
    }
}
