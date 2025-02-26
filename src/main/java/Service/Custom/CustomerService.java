package Service.Custom;

import Model.Customer;
import Service.SuperService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    List<Customer> getAll() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;
    Customer searchCustomer(String customerId) throws SQLException, ClassNotFoundException;
    ObservableList<String> getCustomerId() throws SQLException, ClassNotFoundException;
}
