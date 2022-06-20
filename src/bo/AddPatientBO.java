package bo;

import dto.PatientDTO;

import java.sql.SQLException;

public interface AddPatientBO {
    public boolean savePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
}
