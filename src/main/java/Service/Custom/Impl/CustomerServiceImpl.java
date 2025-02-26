package Service.Custom.Impl;

import Model.Customer;
import Service.Custom.CustomerService;
import Util.DaoType;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.CustomerDao;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static CustomerServiceImpl instance;

    private CustomerServiceImpl(){}

    public static CustomerServiceImpl getInstance(){
        return instance==null? instance = new CustomerServiceImpl():instance;
    }

    CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = customerDao.getAll();
        return customerList;
    }

    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return  customerDao.save(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return customerDao.update(customer);
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDao.delete(customerId);
    }

    @Override
    public Customer searchCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return customerDao.search(customerId);
    }

    @Override
    public ObservableList<String> getCustomerId() throws SQLException, ClassNotFoundException {
        return customerDao.getId();
    }
}
