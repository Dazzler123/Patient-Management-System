package dao.custom.impl;

import dao.custom.MedicineDAO;
import entity.Medicine;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public ArrayList<Medicine> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Medicine> medicines = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Medicine");
        while (resultSet.next()) {
            medicines.add(
                    new Medicine(
                            resultSet.getString("M_ID"),
                            resultSet.getString("name"),
                            resultSet.getString("brand"),
                            resultSet.getString("country"),
                            resultSet.getString("volume"),
                            LocalDate.parse(resultSet.getString("date_of_manufacture")),
                            LocalDate.parse(resultSet.getString("date_of_expiry")),
                            resultSet.getDouble("p_p_unit"),
                            resultSet.getInt("qty_on_hand")
                    )
            );
        }
        return medicines;
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
