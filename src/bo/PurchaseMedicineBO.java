package bo;

import dto.MedicineDTO;
import dto.Medicine_Cart;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PurchaseMedicineBO {
    public ObservableList<MedicineDTO> getAllMedicines() throws SQLException, ClassNotFoundException;
    public boolean saveOrder(Medicine_Cart m) throws SQLException, ClassNotFoundException;
    public int getQty(String id) throws SQLException, ClassNotFoundException;
    public boolean updateQty(int reducedQty, String id) throws SQLException, ClassNotFoundException;
}
