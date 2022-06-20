package bo.custom.impl;

import bo.AttendanceBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AttendanceBOImpl implements AttendanceBO {
    @Override
    public boolean isExists(String id, String date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT time_of_arrival, time_of_departure FROM Attendance WHERE D_ID=? AND date=?",
                id, LocalDate.now());

        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getTimeOfArrival(String id, String date) throws SQLException, ClassNotFoundException {
        String arrivalTime = null;
        ResultSet resultSet = CrudUtil.execute("SELECT time_of_arrival FROM Attendance WHERE D_ID=? AND date=?",
                id, LocalDate.now());

        if (resultSet.next()) {
            arrivalTime = String.valueOf(resultSet.getString("time_of_arrival"));
        }
        return arrivalTime;
    }

    @Override
    public String getTimeOfDeparture(String id, String date) throws SQLException, ClassNotFoundException {
        String departedTime = null;
        ResultSet resultSet = CrudUtil.execute("SELECT time_of_departure FROM Attendance WHERE D_ID=? AND date=?",
                id, LocalDate.now());

        if (resultSet.next()) {
            departedTime = String.valueOf(resultSet.getString("time_of_departure"));
        }
        return departedTime;
    }

    @Override
    public String getName(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM Doctor WHERE D_ID=?", id);
        if (resultSet.next()) {
            return resultSet.getString("name");
        } else {
            return "";
        }
    }

    @Override
    public ObservableList<String> getIDs() throws SQLException, ClassNotFoundException {
        ObservableList<String> idList = FXCollections.observableArrayList();
        ResultSet resultSet = CrudUtil.execute("SELECT D_ID FROM Doctor");
        while (resultSet.next()) {
            idList.add(resultSet.getString("D_ID"));
        }
        return idList;
    }

    @Override
    public boolean saveArrival(String date, String id, String arrive) throws SQLException, ClassNotFoundException {
        if (CrudUtil.execute("INSERT INTO Attendance VALUES (?,?,?,?)",
                date, id, arrive, null)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean saveDeparture(String depart, String date, String id) throws SQLException, ClassNotFoundException {
        if (CrudUtil.execute("UPDATE Attendance SET time_of_departure=? WHERE date=? AND D_ID=?",
                depart, date, id)) {
            return true;
        }
        return false;
    }


}
