package dao.custom;

import entity.Patient;

import java.sql.SQLException;

public interface PatientDAO {
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException;
    public boolean update() throws SQLException, ClassNotFoundException;
}
