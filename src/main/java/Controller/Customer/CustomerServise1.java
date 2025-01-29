package Controller.Customer;

import Model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerServise1 {
    List<Customer> getAll() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;

    Customer searchCustomer(String customerId) throws SQLException, ClassNotFoundException;


}
