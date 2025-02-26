package repository.custom;

import Model.Customer;
import javafx.collections.ObservableList;
import repository.CrudRepository;

import java.sql.SQLException;

public interface CustomerDao extends CrudRepository<Customer,String> {
    ObservableList<String> getId() throws SQLException, ClassNotFoundException;
}
