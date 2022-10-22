package bo.custom.impl;

import bo.custom.ManagePatientBO;
import dao.custom.PatientDAO;
import dao.custom.impl.PatientDAOImpl;
import dto.PatientDTO;
import entity.Patient;

import java.sql.SQLException;

public class ManagePatientBOImpl implements ManagePatientBO {
    //Dependency Injection - property injection
    PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException {
        if(patientDAO.update(new Patient(patientDTO.getNic(), patientDTO.getName(),
                patientDTO.getMobile(), patientDTO.getAddress(), patientDTO.getAge(),
                patientDTO.getGender(), patientDTO.getWeight(), patientDTO.getHistory(),
                patientDTO.getOther_issues()))) {
            return true;
        }
        return false;
    }

    @Override
    public PatientDTO searchPatient(String id) throws SQLException, ClassNotFoundException {
        Patient patient = patientDAO.search(id);
        if(patient != null) {
            return new PatientDTO(patient.getNic(),patient.getName(),patient.getMobile(),
                    patient.getAddress(), patient.getAge(),patient.getGender(),
                    patient.getWeight(),patient.getHistory(),patient.getOther_issues());
        } else {
            return null;
        }
    }

    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        if(patientDAO.delete(id)) {
            return true;
        }
        return false;
    }
}
