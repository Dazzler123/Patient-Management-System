package dao;

import entity.Patient;

import java.sql.SQLException;

public interface PatientDAO {
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException;
}
