package Controller.Item;

import Model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemServise {

    List<Item> getAllItem() throws SQLException, ClassNotFoundException;

    boolean addItem(Item item) throws SQLException, ClassNotFoundException;

    boolean updateItem(Item item) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    Item searchItem(String id) throws SQLException, ClassNotFoundException;
}
