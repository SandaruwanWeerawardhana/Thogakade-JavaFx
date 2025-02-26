package Service.Custom;

import Model.Item;
import Service.SuperService;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface ItemService extends SuperService {
    List<Item> getAll() throws SQLException, ClassNotFoundException;
    boolean save(Item item) throws SQLException, ClassNotFoundException;
    boolean update(Item item) throws SQLException, ClassNotFoundException;
    boolean delete(String Id) throws SQLException, ClassNotFoundException;
    Item search(String Id) throws SQLException, ClassNotFoundException;
    ObservableList<String> getId() throws SQLException, ClassNotFoundException;
}
