package dao.custom;

import entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineDAO {
    public ArrayList<Medicine> getAll() throws SQLException, ClassNotFoundException;
    public int getQty(String id) throws SQLException, ClassNotFoundException;
    public boolean updateQty(int reducedQty, String id) throws SQLException, ClassNotFoundException;
}
