package bo.custom;

import dto.PatientDTO;

import java.sql.SQLException;

public interface ManagePatientBO {
    public boolean updatePatient(PatientDTO patientDTO) throws SQLException, ClassNotFoundException;
    public PatientDTO searchPatient(String id) throws SQLException, ClassNotFoundException;
    public boolean deletePatient();
}
