package Service.Custom.Impl;

import Model.Customer;
import Service.Custom.CustomerService;
import javafx.collections.ObservableList;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static CustomerServiceImpl instance;

    private CustomerServiceImpl(){}

    public static CustomerServiceImpl getInstance(){
        return instance==null? instance=new CustomerServiceImpl():instance;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        System.out.println("dsafasdf"+customer);
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        return false;
    }

    @Override
    public Customer searchCustomer(String customerId) {
        return null;
    }

    @Override
    public ObservableList<String> getCustomerId() {
        return null;
    }
}
