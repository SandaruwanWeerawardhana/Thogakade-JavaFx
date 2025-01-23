package Controller.Customer;

import DBConnection.DBConnection;
import Model.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public TableView tlbCustomer;
    public TableColumn tID;
    public TableColumn tName;
    public TableColumn address;
    public TableColumn Salary;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtcode;
    public JFXTextField txtPrice;
    public JFXTextField txtDes;
    @FXML
    private JFXButton add;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton search;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXButton update;

    @FXML
    void addAction(ActionEvent event) {
        try {

            Customer customer = new Customer(
                    txtID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Double.parseDouble(txtSalary.getText())
            );
            boolean isAdded = CustomerController.getInstance().saveCustomer(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer added").show();
                loadtable();
            }

            cusList.add(new Customer(
                    txtID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Double.parseDouble(txtSalary.getText())
            ));

            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            txtSalary.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void delateaction(ActionEvent event) {

        try {
            boolean isDelete = CustomerController.getInstance().deleteCustomer(txtID.getText());

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer Deleted").show();
                loadtable();
            }

            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            txtSalary.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void searchAction(ActionEvent event) {
        try {
            Customer customer = CustomerController.getInstance().searchCustomer(txtID.getText());
            if (customer != null) {
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtSalary.setText(customer.getSalary() + "");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtId(ActionEvent event) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("Select * from customer where id=?");
            stm.setString(1, txtID.getText());
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );

                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtSalary.setText(customer.getSalary() + "");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateaction(ActionEvent event) {
        try {
            Customer customer = new Customer(
                    txtID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Double.parseDouble(txtSalary.getText())

            );

            boolean isUpdate = CustomerController.getInstance().updateCustomer(customer);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
                loadtable();
            }

            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            txtSalary.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    List<Customer> cusList = new ArrayList<>();

    private void loadtable() {
        try {
            ObservableList<Customer> CusObList = (ObservableList<Customer>) CustomerController.getInstance().getAll();
            cusList.forEach(customer -> {
                CusObList.add(customer);
            });

            tlbCustomer.setItems(CusObList);

//set Model name
            tID.setCellValueFactory(new PropertyValueFactory<>("id"));
            tName.setCellValueFactory(new PropertyValueFactory<>("name"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadtable();
        tlbCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Customer) newValue);
            }
        });
    }

    public void setTextToValues(Customer customer) {
        txtID.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(customer.getSalary()+"");
    }


}
