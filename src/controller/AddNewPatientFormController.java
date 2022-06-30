package controller;

import bo.custom.AddPatientBO;
import bo.custom.impl.AddPatientBOImpl;
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

public class AddNewPatientFormController {
    public JFXTextField txtNIC;
    public JFXTextField txtName;
    public JFXTextField txtMobile;
    public JFXTextField txtAge;
    public JFXTextField txtWeight;
    public JFXTextArea txtAddress;
    public TextArea txtHistory;
    public TextArea txtOtherIssues;
    public JFXComboBox cbxGender;

    //Dependency Injection - property injection
    AddPatientBO addPatientBO = new AddPatientBOImpl();

    public void initialize() {
        //load gender types
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Other");
        cbxGender.setItems(gender);
    }

    public void btnSavePatient(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PatientDTO patientDTO = new PatientDTO(
                txtNIC.getText(),txtName.getText(),
                txtMobile.getText(),txtAddress.getText(),
                Integer.parseInt(txtAge.getText()),String.valueOf(cbxGender.getSelectionModel().getSelectedItem()),
                txtWeight.getText(),txtHistory.getText(),txtOtherIssues.getText()
        );

        //save patient
        if(addPatientBO.savePatient(patientDTO)){
            //confirmation alert
            new Alert(Alert.AlertType.CONFIRMATION,"Patient saved successfully.").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Save patient failed").show();
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
