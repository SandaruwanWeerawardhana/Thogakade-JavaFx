package Controller.Customer;

import DBConnection.DBConnection;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerServise {
    private static CustomerController instance;

    private CustomerController() {
    }

    public static CustomerController getInstance() {
        if (instance == null) instance = new CustomerController();
        return instance;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from customer");
        ObservableList<Customer> cusObList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            cusObList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return cusObList;
    }

    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");

        stm.setString(1, customer.getId());
        stm.setString(2, customer.getName());
        stm.setString(3, customer.getAddress());
        stm.setDouble(4, customer.getSalary());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update Customer set name=?, address=?, salary=? where id=?");
        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        stm.setObject(4, customer.getId());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Delete from customer where id=?");
        stm.setString(1, customerId);
        return stm.executeUpdate() > 0;
    }

    @Override
    public Customer searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from customer where id=?");
        stm.setString(1, customerId);
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            return customer;
        }
        return null;
    }

    public ObservableList<String> getCustomerID() throws SQLException, ClassNotFoundException {
        ObservableList<String> customerList = FXCollections.observableArrayList();

        getAll().forEach(customer -> customerList.add(customer.getId()));
        return customerList;

    }
}
