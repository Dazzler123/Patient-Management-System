package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.UINavigation;

import java.io.IOException;

public class AdminLoginFormControlller {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.equals("admin") & password.equals("1234")) {
            UINavigation.setUI("AdminForm", "Administrator Controls");

            //close login ui
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }else{
            new Alert(Alert.AlertType.WARNING, "Incorrect Username or Password!").show();
        }
    }

    public void btnExit(ActionEvent actionEvent) {
        Stage stage =(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
