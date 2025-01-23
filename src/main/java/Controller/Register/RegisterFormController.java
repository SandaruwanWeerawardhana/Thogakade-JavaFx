package Controller.Register;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class RegisterFormController {
    public JFXTextField txtuserNAme;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;

    public void btnAddAction(ActionEvent actionEvent) {

        try {

            if (RegisterController.add(
                    txtuserNAme.getText(),
                    txtEmail.getText(),
                    txtPassword.getText())
            ) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registered").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Registered Fail").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
