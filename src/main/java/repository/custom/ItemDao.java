package repository.custom;

import Model.Customer;
import Model.Item;
import javafx.collections.ObservableList;
import repository.CrudRepository;

import java.sql.SQLException;

public interface ItemDao extends CrudRepository<Item,String> {
    ObservableList<String> getId() throws SQLException, ClassNotFoundException;
}
