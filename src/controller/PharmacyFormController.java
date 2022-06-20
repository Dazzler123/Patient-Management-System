package controller;

import bo.PurchaseMedicineBO;
import bo.custom.impl.PurchaseMedicineBOImpl;
import com.jfoenix.controls.JFXButton;
import dto.MedicineDTO;
import dto.Medicine_Cart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.DateAndTime;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacyFormController {
    public Label lblDate;
    public Label lblTime;
    public Label lblAM_PM;
    public TableView tblMedicine;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colBrand;
    public TableColumn colCountry;
    public TableColumn colVolume;
    public TableColumn colDate_manu;
    public TableColumn colDate_exp;
    public TableColumn colP_P_Unit;
    public TableColumn colQtyOnHand;
    public TableView tblMedicineCart;
    public TableColumn colItemID;
    public TableColumn colItemName;
    public TableColumn colItemP_P_Unit;
    public TableColumn colItemQty;
    public TableColumn colTotal;
    public JFXButton btnAddItem;
    public JFXButton btnRemoveItem;
    public TextField txtQty;
    public Label lblTotalAmount;
    public JFXButton btnConfirm;

    //cart list
    private final ArrayList<Medicine_Cart> medicineCart = new ArrayList();

    //Dependency injection - property injection
    PurchaseMedicineBO purchaseMedicineBO = new PurchaseMedicineBOImpl();

    //total amount
    private double totalAmount;

    public void initialize() throws SQLException, ClassNotFoundException {
        //load date and time
        DateAndTime.loadDateAndTime(lblDate, lblTime, lblAM_PM);

        //btns to be disabled at the startup
        txtQty.setDisable(true);
        btnAddItem.setDisable(true);
        btnRemoveItem.setDisable(true);
        btnConfirm.setDisable(true);

        //initialize tables
        colID.setCellValueFactory(new PropertyValueFactory<>("M_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        colDate_manu.setCellValueFactory(new PropertyValueFactory<>("date_of_manufacture"));
        colDate_exp.setCellValueFactory(new PropertyValueFactory<>("date_of_expiry"));
        colP_P_Unit.setCellValueFactory(new PropertyValueFactory<>("p_p_unit"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty_on_hand"));

        colItemID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemP_P_Unit.setCellValueFactory(new PropertyValueFactory<>("p_p_unit"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        //load all medicines to the tblMedicine
        loadAllMedicines();

        tblMedicine.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //enable btns to add an medicine to cart
            txtQty.setDisable(false);
            btnAddItem.setDisable(false);
        });

        tblMedicineCart.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //enable remove btn to remove a medicine
            btnRemoveItem.setDisable(false);
        });

    }

    private void loadAllMedicines() throws SQLException, ClassNotFoundException {
        tblMedicine.setItems(purchaseMedicineBO.getAllMedicines());
    }

    public void btnAddItem(ActionEvent actionEvent) {
        MedicineDTO medicineDTO = (MedicineDTO) tblMedicine.getSelectionModel().getSelectedItem();
        BigDecimal p_p_unit = medicineDTO.getP_p_unit();
        int qty = Integer.parseInt(String.valueOf(txtQty.getText()));

        //calculate total for each
        double total = calculateTotalForEach(p_p_unit, qty);

        medicineCart.add(
                new Medicine_Cart(
                        medicineDTO.getM_ID(),
                        medicineDTO.getName(),
                        medicineDTO.getP_p_unit(),
                        qty, total));

        //refresh cart table
        loadToCart();

        //enable confirm btn to confirm if needed
        btnConfirm.setDisable(false);

    }

    private void loadToCart() {
        //initialize the total amount with 0
        totalAmount = 0;

        ArrayList<Medicine_Cart> itemList = medicineCart;
        ObservableList<Medicine_Cart> cart = FXCollections.observableArrayList(itemList);
        tblMedicineCart.setItems(cart);

        //calculate total amount
        for (Medicine_Cart m : itemList) {
            totalAmount += m.getTotal(); //totalAmount = totalAmount + m.getTotal();
        }

        //set total amount to the label
        lblTotalAmount.setText(String.valueOf(totalAmount));
    }

    private double calculateTotalForEach(BigDecimal p_p_unit, int qty) {
        double each = Double.parseDouble(String.valueOf(p_p_unit));
        return each * qty;
    }

    public void btnRemoveItem(ActionEvent actionEvent) {
        Medicine_Cart medicine_cart = (Medicine_Cart) tblMedicineCart.getSelectionModel().getSelectedItem();
        medicineCart.remove(medicine_cart);

        //refresh table
        loadToCart();
    }

    public void btnConfirm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<Medicine_Cart> cartList = medicineCart;
        boolean b = false;
        for (Medicine_Cart m : cartList) {
            if (purchaseMedicineBO.saveOrder(m)) {
                b = true;
            }
        }
        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "Purchased medicines confirmed.").show();
        }

        //disable confirm btn once purchase is placed
        btnConfirm.setDisable(true);

        //////////UPDATE Qty/////////////////////////
        for (Medicine_Cart m : cartList) {
            String id = m.getId();
            int qty_on_hand = 0;
            int reducedQty;

            //get qty_on_hand
            qty_on_hand = purchaseMedicineBO.getQty(id);

            //if the existing qty is NOT 0
            if (!(qty_on_hand == 0)) {

                reducedQty = qty_on_hand - m.getQty();
                purchaseMedicineBO.updateQty(reducedQty ,id);
            }
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
