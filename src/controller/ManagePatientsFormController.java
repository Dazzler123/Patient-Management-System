package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

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

    public void btnExit(ActionEvent actionEvent) {
    }

    public void btnDeletePatient(ActionEvent actionEvent) {
    }

    public void btnUpdatePatient(ActionEvent actionEvent) {

    }

    public void txtSearchPatient(ActionEvent actionEvent) {
        System.out.println("Entered");
    }
}
