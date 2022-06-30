package bo.custom.impl;

import bo.custom.PurchaseMedicineBO;
import dao.custom.MedicineDAO;
import dao.custom.impl.MedicineDAOImpl;
import dto.MedicineDTO;
import dto.Medicine_Cart;
import entity.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PurchaseMedicineBOImpl implements PurchaseMedicineBO {

    //Dependency Injection - property injection
    private final MedicineDAO medicineDAO = new MedicineDAOImpl();

    @Override
    public ObservableList<MedicineDTO> getAllMedicines() throws SQLException, ClassNotFoundException {
        ObservableList<MedicineDTO> medicineList = FXCollections.observableArrayList();
        for(Medicine m : medicineDAO.getAll()){
            medicineList.add(new MedicineDTO(m.getM_ID(),m.getName(),m.getBrand(),m.getCountry(),m.getVolume(),
                    m.getDate_of_manufacture(),m.getDate_of_expiry(),new BigDecimal(m.getP_p_unit()),m.getQty_on_hand()));
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
       return medicineDAO.getQty(id);
    }

    @Override
    public boolean updateQty(int reducedQty, String id) throws SQLException, ClassNotFoundException {
      return medicineDAO.updateQty(reducedQty,id);
    }


}
