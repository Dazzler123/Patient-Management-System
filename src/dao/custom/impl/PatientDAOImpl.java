package dao.custom.impl;

import dao.custom.PatientDAO;
import entity.Patient;
import util.CrudUtil;

import java.sql.SQLException;

public class PatientDAOImpl implements PatientDAO {

    @Override
    public boolean save(Patient patient) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("INSERT INTO Patient VALUES (?,?,?,?,?,?,?,?,?)",
                patient.getNic(),patient.getName(),patient.getMobile(),
                patient.getAddress(),patient.getAge(),patient.getGender(),
                patient.getWeight(),patient.getHistory(),patient.getOther_issues())){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("UPDATE PATIENT SET name=?, mobile=?,address=?, age=?, gender=?, weight=?, history=?, other_issues=? WHERE nic=?",
                patient.getName(),patient.getMobile(),patient.getAddress(),patient.getAge(),
                patient.getGender(),patient.)) {

        }
        return false;
    }
}
