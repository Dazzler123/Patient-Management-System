package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import util.DateAndTime;
import util.UINavigation;

import java.io.IOException;

public class DashBoardFormController {
    public Label lblDate;
    public Label lblTime;
    public Label lblAM_PM;

    public void initialize(){
        DateAndTime.loadDateAndTime(lblDate,lblTime,lblAM_PM);
    }
    public void btnToOPD(ActionEvent actionEvent) throws IOException {
        UINavigation.setUI("OPDForm","OPD");
    }

    public void btnToPharmacy(ActionEvent actionEvent) throws IOException {
        UINavigation.setUI("PharmacyForm","Pharmacy");
    }

    public void btnToDoctorAttendance(ActionEvent actionEvent) throws IOException {
        UINavigation.setUI("DoctorAttendanceForm","Record Doctor Attendance");
    }

    public void btnToAdminLogin(ActionEvent actionEvent) throws IOException {
        UINavigation.setUI("AdminLoginForm","Administrator Login");
    }

    public void btnExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }

}
