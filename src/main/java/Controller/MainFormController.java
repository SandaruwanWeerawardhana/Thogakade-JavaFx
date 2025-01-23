package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane btnItem;
    public Button btnCustomer;
    public AnchorPane lodePage;

    public void btnItemAction(MouseEvent mouseEvent) {
    }

    public void btnCustomerAction(ActionEvent actionEvent)  {
        try {
            LodeCustomerPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void LodeCustomerPage() throws IOException {
        URL resource = this.getClass().getResource("/View/CustomerView.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        this.lodePage.getChildren().clear();
        this.lodePage.getChildren().add(load);
    }

    public void btnitemAction(ActionEvent actionEvent) throws IOException {
        lodePage.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/ItemView.fxml"));
        lodePage.getChildren().add((Node) load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LodeCustomerPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnOrderAction(ActionEvent actionEvent) throws IOException {
        lodePage.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Order.fxml"));
        lodePage.getChildren().add((Node) load);
    }
}
