package repository.custom.impl;

import DBConnection.DBConnection;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.CustomerDao;

import java.sql.*;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer entity) {
        String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, entity.getId());
            psTm.setObject(2, entity.getName());
            psTm.setObject(3, entity.getAddress());
            psTm.setObject(4, entity.getSalary());
            return psTm.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(Customer entity) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("Update customer set Name=?, Address=?, Salary=? where CustID=?");
            stm.setObject(1, entity.getName());
            stm.setObject(2, entity.getAddress());
            stm.setObject(3, entity.getSalary());
            stm.setObject(4, entity.getId());
            return stm.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Customer search(String s) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("Select * from customer where CustID=?");
            stm.setString(1, s);
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(String s) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("Delete from customer where CustID=?");
            stm.setString(1, s);
            return stm.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Customer> getAll() {
        try {
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
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<String> getId() throws SQLException, ClassNotFoundException {
        ObservableList<String> custom = FXCollections.observableArrayList();

        String query = "SELECT CustID FROM customer";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery(query);

        while (resultSet.next()) {
            custom.add(resultSet.getString("CustID"));
        }
        return custom;
    }
}
