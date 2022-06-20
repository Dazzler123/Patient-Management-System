package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Prescription_Cart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.CrudUtil;
import util.DateAndTime;
import util.UINavigation;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OPDFormController {
    public Label lblDate;
    public Label lblTime;
    public Label lblAM_PM;
    public Text lblName;
    public Text lblMobile;
    public Text lblAddress;
    public Text lblAge;
    public Text lblGender;
    public Text lblWeight;
    public TextArea txtHistory;
    public TextArea txtOtherIssues;
    public JFXComboBox cbxMedicineID;
    public TextField txtPerDose;
    public TextField txtTimesADay;
    public TextField txtNoOfDays;
    public JFXComboBox cbxDrugPlan;
    public Text lblPresID;
    public TextArea txtDescription;
    public TableView tblPres;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colPerDose;
    public TableColumn colTimesADay;
    public TableColumn colNoOfDays;
    public TableColumn colDrugPlan;
    public JFXComboBox cbxDoctorID;
    public Label lblDoctorName;
    public JFXButton btnAddtoPres;
    public JFXButton btnConfirm;
    public Label lblMedicineName;
    public Text lblConsulID;
    public JFXTextField txtNIC;

    //prescription cart
    private ArrayList<Prescription_Cart> pres_cart = new ArrayList<>();

    public void initialize() throws SQLException, ClassNotFoundException {
        //set date and time
        DateAndTime.loadDateAndTime(lblDate, lblTime, lblAM_PM);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPerDose.setCellValueFactory(new PropertyValueFactory<>("per_dose"));
        colTimesADay.setCellValueFactory(new PropertyValueFactory<>("times_a_day"));
        colNoOfDays.setCellValueFactory(new PropertyValueFactory<>("no_of_days"));
        colDrugPlan.setCellValueFactory(new PropertyValueFactory<>("drug_plan"));

        //disable btns at startup
        btnAddtoPres.setDisable(true);
        btnConfirm.setDisable(true);

        //set drug plans
        ObservableList<String> drugPlanList = FXCollections.observableArrayList("Before Meal", "After Meal");
        cbxDrugPlan.setItems(drugPlanList);

        //===========================================

        //generate new prescription id
        generatePrescriptionID();
        //generate new consultation id
        generateConsultationID();

        //load all doctor ids to cbx
        loadAllDoctorIDs();

        //load all medicine ids to cbx
        loadAllMedicineIDs();

        //get medicine id selection
        cbxMedicineID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                //get medicine name
                getMedicineName(String.valueOf(newValue));
                //enable add to prescription btn
                btnAddtoPres.setDisable(false);
                //clear textfields relevant to medicine
                txtPerDose.clear();
                txtNoOfDays.clear();
                txtTimesADay.clear();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        //get doctor id selection
        cbxDoctorID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                //get doctor's name
                getDoctorName(String.valueOf(newValue));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void generateConsultationID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT MAX(Consult_ID) FROM Consultation");
        if (rs.next()) {
            //rs.getString("MAX(Consult_ID)");
            if (rs.getString("MAX(Consult_ID)") == null) {
                lblConsulID.setText("C001");
            } else {
                Long consultID = Long.parseLong(rs.getString("MAX(Consult_ID)").substring(2, rs.getString("MAX(Consult_ID)").length()));
                consultID++;
                lblConsulID.setText("C0" + String.format("%02d", consultID));
            }
        }
    }

    private void generatePrescriptionID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT MAX(Pres_ID) FROM Prescription");
        if (rs.next()) {
            //rs.getString("MAX(PresID)");
            if (rs.getString("MAX(Pres_ID)") == null) {
                lblPresID.setText("P001");
            } else {
                Long presID = Long.parseLong(rs.getString("MAX(Pres_ID)").substring(2, rs.getString("MAX(Pres_ID)").length()));
                presID++;
                lblPresID.setText("P0" + String.format("%02d", presID));
            }
        }
    }

    private void getDoctorName(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM Doctor WHERE D_ID=?", id);
        if (resultSet.next()) {
            lblDoctorName.setText(resultSet.getString("name"));
        } else {
            lblDoctorName.setText("");
        }
    }

    private void getMedicineName(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM Medicine WHERE M_ID=?", id);
        if (resultSet.next()) {
            lblMedicineName.setText(resultSet.getString("name"));
        } else {
            lblMedicineName.setText("");
        }
    }

    private void loadAllMedicineIDs() throws SQLException, ClassNotFoundException {
        ObservableList<String> medicineIDList = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT M_ID FROM Medicine");
        while (resultSet.next()) {
            medicineIDList.add(resultSet.getString("M_ID"));
        }
        cbxMedicineID.setItems(medicineIDList);
    }

    private void loadAllDoctorIDs() throws SQLException, ClassNotFoundException {
        ObservableList<String> doctorIDList = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT D_ID FROM Doctor");
        while (resultSet.next()) {
            doctorIDList.add(resultSet.getString("D_ID"));
        }
        cbxDoctorID.setItems(doctorIDList);
    }

    //search patient
    public void txtSearchNICOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Patient WHERE nic=?", txtNIC.getText());
        if (resultSet.next()) {
            lblName.setText(resultSet.getString("name"));
            lblMobile.setText(resultSet.getString("mobile"));
            lblAddress.setText(resultSet.getString("address"));
            lblAge.setText(String.valueOf(resultSet.getInt("age")));
            lblGender.setText(resultSet.getString("gender"));
            lblWeight.setText(resultSet.getString("weight"));
            txtHistory.setText(resultSet.getString("history"));
            txtOtherIssues.setText(resultSet.getString("other_issues"));

            //confirmation alert
            new Alert(Alert.AlertType.CONFIRMATION, "Patient Already exists.").show();

            //disable patient nic textfield
            txtNIC.setDisable(true);
        } else {
            //error alert
            new Alert(Alert.AlertType.ERROR, "No matching patient found!\nPlease add a new Patient.").show();

            //clear all textfields and labels related to patient
            clearAllLabels();
        }
    }

    private void clearAllLabels() {
        //enable patient nic textfield
        txtNIC.setDisable(false);

        txtNIC.clear();
        lblName.setText("");
        lblMobile.setText("");
        lblAddress.setText("");
        lblAge.setText("");
        lblWeight.setText("");
        lblGender.setText("");
        txtHistory.clear();
        txtOtherIssues.clear();
    }

    public void btnAddPatient(ActionEvent actionEvent) throws IOException {
        //clear all textfields and labels related to patient
        clearAllLabels();
        UINavigation.setUI("AddNewPatientForm", "Add New Patient");
    }

    public void btnAddToPres(ActionEvent actionEvent) {
        String id = String.valueOf(cbxMedicineID.getSelectionModel().getSelectedItem());
        String name = String.valueOf(lblMedicineName.getText());
        String per_dose = String.valueOf(txtPerDose.getText());
        String times_a_day = String.valueOf(txtTimesADay.getText());
        int no_of_days = Integer.parseInt(String.valueOf(txtNoOfDays.getText()));
        String drug_plan = String.valueOf(cbxDrugPlan.getSelectionModel().getSelectedItem());

        //add medicine to prescription cart
        pres_cart.add(
                new Prescription_Cart(
                        id, name, per_dose, times_a_day, no_of_days, drug_plan
                ));

        //refresh cart
        loadToCart();

        //enable confirm btn
        enableConfirmBtn();

    }

    private void enableConfirmBtn() {
        if (!pres_cart.isEmpty()) {
            //enable confirm btn is cart is not empty
            btnConfirm.setDisable(false);
        }
    }

    private void loadToCart() {
        ObservableList<Prescription_Cart> prescriptionCart = FXCollections.observableArrayList();
        for (Prescription_Cart p : pres_cart) {
            prescriptionCart.add(
                    new Prescription_Cart(
                            p.getId(), p.getName(), p.getPer_dose(), p.getTimes_a_day(), p.getNo_of_days(), p.getDrug_plan()
                    ));
        }
        tblPres.setItems(prescriptionCart);
    }

    private int getQtyOnHand(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT qty_on_hand FROM Medicine WHERE M_ID=?", id);
        if (resultSet.next()) {
            return resultSet.getInt("qty_on_hand");
        }
        return 0;
    }

    public void btnConfirm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String consultID = String.valueOf(lblConsulID.getText());
        String presID = String.valueOf(lblPresID.getText());
        String D_ID = String.valueOf(cbxDoctorID.getSelectionModel().getSelectedItem());
        String nic = String.valueOf(txtNIC.getText());
        String description = String.valueOf(txtDescription.getText());

        boolean consultation = false;
        boolean prescription = false;


        ///transaction danna ona

        //insert to consultation table
        if (CrudUtil.execute("INSERT INTO Consultation VALUES (?,?,?,?,?,?)",
                consultID, LocalDate.now(), String.valueOf(LocalTime.now()), nic, description, D_ID)) {
            consultation = true;
        }

        for (Prescription_Cart p : pres_cart) {
            String id = String.valueOf(p.getId());
            int per_dose = Integer.parseInt(String.valueOf(p.getPer_dose()));
            int times_a_day = Integer.parseInt(String.valueOf(p.getTimes_a_day()));
            int no_of_days = Integer.parseInt(String.valueOf(p.getNo_of_days()));
            String drugPlan = String.valueOf(p.getDrug_plan());

            //insert to prescription table
            if (CrudUtil.execute("INSERT INTO Prescription VALUES (?,?,?,?,?,?,?,?,?,?)",
                    presID, LocalDate.now(), String.valueOf(LocalTime.now()),
                    id, D_ID, nic, per_dose, times_a_day, no_of_days, drugPlan)) {
                prescription = true;
            }

            //////UPDATE MEDICINE QTY//////////////

            //calculate amount bought
            int qtyBought = per_dose * times_a_day * no_of_days;

            int availableQty = getQtyOnHand(id);
            //reduce from available qty on hand
            int finalQty = availableQty - qtyBought;

            CrudUtil.execute("UPDATE Medicine SET qty_on_hand=? WHERE M_ID=?", finalQty, id);
        }

        if (consultation & prescription) {
            //confirmation alert
            new Alert(Alert.AlertType.CONFIRMATION, "Consultation confirmed.").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something is wrong!").show();
        }

    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

}
