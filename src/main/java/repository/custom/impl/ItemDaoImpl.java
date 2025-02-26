package repository.custom.impl;

import DBConnection.DBConnection;
import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.ItemDao;

import java.sql.*;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Insert into Item Values(?,?,?,?)");
        stm.setString(1, entity.getCode());
        stm.setString(2, entity.getDescription());
        stm.setDouble(3, entity.getUnitPrice());
        stm.setInt(4, entity.getQtyOnHand());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psm = connection.prepareStatement("Update Item set Description=?, UnitPrice=?, QtyOnHand=? where ItemCode=?");
        psm.setObject(1, entity.getDescription());
        psm.setObject(2, entity.getUnitPrice());
        psm.setObject(3, entity.getQtyOnHand());
        psm.setObject(4, entity.getCode());
        return psm.executeUpdate() > 0;
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Select * from Item where ItemCode=?");
        stm.setString(1, s);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Delete from Item where ItemCode=?");
        stm.setString(1, s);
        return stm.executeUpdate() > 0;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet resultSet = stm.executeQuery("Select * from Item");
        ObservableList<Item> ItemOb = FXCollections.observableArrayList();

        while (resultSet.next()) {
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
    public ObservableList<String> getId() throws SQLException, ClassNotFoundException {
        ObservableList<String> itemIds = FXCollections.observableArrayList();

        String query = "SELECT ItemCode FROM Item";
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery(query);

        while (resultSet.next()) {
            itemIds.add(resultSet.getString("ItemCode"));
        }
        return itemIds;
    }
}
