package controller;

import bo.AttendanceBO;
import bo.custom.impl.AttendanceBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.DateAndTime;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorAttendanceFormController {

    public Label lblDate;
    public Label lblTime;
    public Label lblAM_PM;
    public JFXComboBox cbxDoctorID;
    public Label lblDoctorName;
    public JFXButton btnArrival;
    public JFXButton btnDeparture;

    private LocalTime arrivedTime;
    private LocalTime departedTime;


    //Dependancy injection - property injection
    private final AttendanceBO attendanceBO = new AttendanceBOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        //load date and time
        DateAndTime.loadDateAndTime(lblDate, lblTime, lblAM_PM);

        //disable arrival and departure btns at startup
        btnArrival.setDisable(true);
        btnDeparture.setDisable(true);

        //load all doctor ids to the combobox
        loadAllIDs();

        //get selected doctor id
        cbxDoctorID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                //disable arrival and departure btns
                btnArrival.setDisable(true);
                btnDeparture.setDisable(true);

                //set doctor name
                getDoctorName(String.valueOf(newValue));

                //enable arrival or departure btns
                enableDisableBtns(String.valueOf(newValue));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void enableDisableBtns(String id) throws SQLException, ClassNotFoundException {
        String date = String.valueOf(LocalDate.now());

        if (attendanceBO.isExists(id, date)) {
            if (attendanceBO.getTimeOfArrival(id, date).equals("null")) {
                btnArrival.setDisable(false);
            }

            if (attendanceBO.getTimeOfDeparture(id, date).equals("null")) {
                btnDeparture.setDisable(false);
            }

            if (!btnArrival.isDisable() & btnDeparture.isDisable()) {
                btnDeparture.setDisable(true);
            }
        } else {
            btnArrival.setDisable(false);
        }
    }

    private void getDoctorName(String newValue) throws SQLException, ClassNotFoundException {
        String id = String.valueOf(newValue);
        lblDoctorName.setText(attendanceBO.getName(id));
    }

    private void loadAllIDs() throws SQLException, ClassNotFoundException {
        cbxDoctorID.setItems(attendanceBO.getIDs());
    }

    public void btnRecordArrival(ActionEvent actionEvent) {
        //record time of arrival
        arrivedTime = LocalTime.now();
    }

    public void btnRecordDeparture(ActionEvent actionEvent) {
        //record time of departure
        departedTime = LocalTime.now();
    }

    public void btnConfirm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = String.valueOf(cbxDoctorID.getSelectionModel().getSelectedItem());
        String date = String.valueOf(LocalDate.now());

        String arrive = String.valueOf(arrivedTime);
        String depart = String.valueOf(departedTime);

        boolean arr = false;
        boolean dep = false;

        if (!(arrive.equals("null"))) {
            //save arrival time
            arr = attendanceBO.saveArrival(date, id, arrive);

        } else if (!(depart.equals("null"))) {
            //save departure time
            dep = attendanceBO.saveDeparture(depart, date, id);
        }

        //confirmation alert
        if (arr) {
            new Alert(Alert.AlertType.CONFIRMATION, "Arrival Confirmed.\n" + "Welcome Doctor!").show();

        } else if (dep) {
            new Alert(Alert.AlertType.CONFIRMATION, "Departure Confirmed.\n" + "Goodbye Doctor!").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Failed!").show();
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
