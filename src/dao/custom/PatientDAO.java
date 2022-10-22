package dao.custom;

import entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PatientDAO {
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException;
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException;
    public Patient search(String id) throws SQLException, ClassNotFoundException;
}
