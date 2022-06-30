package bo.custom.impl;

import bo.custom.PurchaseMedicineBO;
import dto.MedicineDTO;
import dto.Medicine_Cart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PurchaseMedicineBOImpl implements PurchaseMedicineBO {

    @Override
    public ObservableList<MedicineDTO> getAllMedicines() throws SQLException, ClassNotFoundException {
        ObservableList<MedicineDTO> medicineList = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Medicine");
        while (resultSet.next()) {
            medicineList.add(
                    new MedicineDTO(
                            resultSet.getString("M_ID"),
                            resultSet.getString("name"),
                            resultSet.getString("brand"),
                            resultSet.getString("country"),
                            resultSet.getString("volume"),
                            LocalDate.parse(resultSet.getString("date_of_manufacture")),
                            LocalDate.parse(resultSet.getString("date_of_expiry")),
                            new BigDecimal(resultSet.getString("p_p_unit")),
                            resultSet.getInt("qty_on_hand")
                    )
            );
        }
        return medicineList;
    }

    @Override
    public boolean saveOrder(Medicine_Cart m) throws SQLException, ClassNotFoundException {
        if (CrudUtil.execute("INSERT INTO Purchase_Medicine VALUES (?,?,?,?,?,?,?)",
                LocalDate.now(), LocalTime.now(), m.getId(), m.getName(), m.getP_p_unit(),
                m.getQty(), m.getTotal())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getQty(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT qty_on_hand FROM medicine WHERE M_ID=?", id);
        if(resultSet.next()){
            return resultSet.getInt("qty_on_hand");
        }
        return 0;
    }

    @Override
    public boolean updateQty(int reducedQty, String id) throws SQLException, ClassNotFoundException {
        if(CrudUtil.execute("UPDATE Medicine SET qty_on_hand=? WHERE M_ID=?", reducedQty, id)){
            return true;
        }
        return false;
    }


}
