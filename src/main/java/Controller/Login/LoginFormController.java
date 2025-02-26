package Controller.Login;

import Model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtPassweord;
    public TextField txtuserName;

    public void btnlogin(ActionEvent actionEvent) {
        String key = "1234";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        try {
            Login login = LoginController.check(txtuserName.getText());
            if (login != null) {
                if (basicTextEncryptor.decrypt(login.getPassword()).equals(txtPassweord.getText())) {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Main_Form.fxml"))));
                    stage.show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "check password").show();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Login Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnRegisterAction(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Register.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}