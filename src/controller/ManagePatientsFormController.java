package controller;

import bo.custom.ManagePatientBO;
import bo.custom.impl.ManagePatientBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dto.PatientDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ManagePatientsFormController {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtMobile;
    public JFXTextField txtAge;
    public JFXTextField txtWeight;
    public JFXTextArea txtAddress;
    public TextArea txtHistory;
    public TextArea txtOtherIssues;
    public JFXComboBox cbxGender;

    //Dependency injection - property injection
    ManagePatientBO managePatientBO = new ManagePatientBOImpl();

    public void initialize(){
        //load gender types
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Other");
        cbxGender.setItems(gender);
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void btnDeletePatient(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(managePatientBO.deletePatient(txtNIC.getText())){
            //confirmation alert
            new Alert(Alert.AlertType.CONFIRMATION,"Patient deleted.").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    public void btnUpdatePatient(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(managePatientBO.updatePatient(new PatientDTO(
                txtNIC.getText(), txtName.getText(), txtMobile.getText(),
                txtAddress.getText(), Integer.parseInt(txtAge.getText()), String.valueOf(cbxGender.getSelectionModel().getSelectedItem()),
                txtWeight.getText(), txtHistory.getText(),txtOtherIssues.getText()))) {
            //confirmation alert
            new Alert(Alert.AlertType.CONFIRMATION,"Patient details updated.").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    public void txtSearchPatient(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PatientDTO patientDTO = managePatientBO.searchPatient(txtNIC.getText());
        if(patientDTO != null){
            txtNIC.setText(patientDTO.getNic());
            txtName.setText(patientDTO.getName());
            txtMobile.setText(patientDTO.getMobile());
            txtAddress.setText(patientDTO.getAddress());
            txtAge.setText(String.valueOf(patientDTO.getAge()));
            cbxGender.setValue(String.valueOf(patientDTO.getGender()));
            txtWeight.setText(patientDTO.getWeight());
            txtHistory.setText(patientDTO.getHistory());
            txtOtherIssues.setText(patientDTO.getOther_issues());
        } else {
            new Alert(Alert.AlertType.ERROR,"No Such Patient found!").show();
        }
    }
}
