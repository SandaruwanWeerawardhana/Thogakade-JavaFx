package Controller.Item;

import DBConnection.DBConnection;
import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import java.sql.*;
import java.util.List;

public class ItemController implements ItemServise {
    private static ItemController Instance;

    private ItemController() {

    }

    public static ItemController getInstance() {
        return Instance == null ? Instance = new ItemController() : Instance;
    }

    @Override
    public List<Item> getAllItem() throws SQLException, ClassNotFoundException {
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery("Select * from Item");
        ObservableList<Item> ItemOb = FXCollections.observableArrayList();

        while(resultSet.next()){
            ItemOb.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return ItemOb;
    }

    @Override
    public boolean addItem(Item item) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into Item Values(?,?,?,?)");
        stm.setString(1, item.getCode());
        stm.setString(2, item.getDescription());
        stm.setDouble(3, item.getUnitPrice());
        stm.setInt(4, item.getQtyOnHand());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        PreparedStatement psm = DBConnection.getInstance().getConnection().prepareStatement("Select * from Item where code=?");
        psm.setObject(1,item.getDescription());
        psm.setObject(2,item.getUnitPrice());
        psm.setObject(3,item.getQtyOnHand());
        psm.setObject(4,item.getCode());
        return psm.executeUpdate()>0;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Delete from Item where code=?");
        stm.setString(1,id);
        return stm.executeUpdate()>0;
    }

    @Override
    public Item searchItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Select * from Item where code=?");
        stm.setString(1, id);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()){
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public ObservableList<String> getItemID() throws SQLException, ClassNotFoundException {
        ObservableList<String> ItemList = FXCollections.observableArrayList();

        getAllItem().forEach(item -> ItemList.add(item.getCode()));
        return ItemList;

    }
}
