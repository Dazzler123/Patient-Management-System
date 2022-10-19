package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import util.UINavigation;

import java.io.IOException;

public class AdminFormController {

    public void btnToManagePatients(ActionEvent actionEvent) throws IOException {
        //load manage patients UI
        UINavigation.setUI("ManagePatientsForm","Manage Patients");
    }

    public void btnToManageMedicines(ActionEvent actionEvent) {
    }

    public void btnToManageDoctors(ActionEvent actionEvent) {
    }

    public void btnToReports(ActionEvent actionEvent) {
    }
}
