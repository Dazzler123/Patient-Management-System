package bo;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalTime;

public interface AttendanceBO {
    public boolean isExists(String id, String date) throws SQLException, ClassNotFoundException;
    public String getTimeOfArrival(String id, String date) throws SQLException, ClassNotFoundException;
    public String getTimeOfDeparture(String id, String date) throws SQLException, ClassNotFoundException;
    public String getName(String id) throws SQLException, ClassNotFoundException;
    public ObservableList<String> getIDs() throws SQLException, ClassNotFoundException;
    public boolean saveArrival(String date, String id, String arrive) throws SQLException, ClassNotFoundException;
    public boolean saveDeparture(String depart, String date, String id) throws SQLException, ClassNotFoundException;
}
