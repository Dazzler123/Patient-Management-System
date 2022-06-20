package bo.custom.impl;

import bo.AddPatientBO;
import dao.PatientDAO;
import dao.custom.impl.PatientDAOImpl;
import dto.PatientDTO;
import entity.Patient;

import java.sql.SQLException;

public class AddPatientBOImpl implements AddPatientBO {

    //Dependency Injection - property injection
    PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        if (patientDAO.save(new Patient(patientDTO.getNic(), patientDTO.getName(),
                patientDTO.getMobile(), patientDTO.getAddress(), patientDTO.getAge(),
                patientDTO.getGender(), patientDTO.getWeight(), patientDTO.getHistory(),
                patientDTO.getOther_issues()))) {
            return true;
        }
        return false;
    }
}
