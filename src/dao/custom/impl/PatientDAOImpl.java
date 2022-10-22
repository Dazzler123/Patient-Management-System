package dao.custom.impl;

import dao.custom.PatientDAO;
import entity.Patient;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException {
        if (CrudUtil.execute("INSERT INTO Patient VALUES (?,?,?,?,?,?,?,?,?)",
                patient.getNic(), patient.getName(), patient.getMobile(),
                patient.getAddress(), patient.getAge(), patient.getGender(),
                patient.getWeight(), patient.getHistory(), patient.getOther_issues())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        if (CrudUtil.execute("UPDATE Patient SET name=?, mobile=?, address=?, age=?, gender=?, weight=?, history=?, other_issues=? WHERE nic=?",
                patient.getName(), patient.getMobile(),
                patient.getAddress(), patient.getAge(), patient.getGender(),
                patient.getWeight(), patient.getHistory(), patient.getOther_issues(), patient.getNic())) {
            return true;
        }
        return false;
    }

    @Override
    public Patient search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Patient WHERE nic=?", id);
        if (resultSet.next()) {
            return new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("DELETE FROM Patient WHERE nic=?",id)) {
            return true;
        }
        return false;
    }
}
