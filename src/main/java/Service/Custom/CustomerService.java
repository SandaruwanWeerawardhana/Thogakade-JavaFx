package Service.Custom;

import Model.Customer;
import Service.SuperService;
import javafx.collections.ObservableList;

import java.util.List;

public interface CustomerService extends SuperService {
    List<Customer> getAll();
    boolean saveCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String customerId);
    Customer searchCustomer(String customerId);
    ObservableList<String> getCustomerId();
}
