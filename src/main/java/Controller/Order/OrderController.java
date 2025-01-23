package Controller.Order;

import Controller.Item.ItemFormControlller;
import DBConnection.DBConnection;
import Model.Item;
import Model.Order;
import Model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {

    public static boolean placeOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("Insert Into orders Values(?,?,?)");
            stm.setString(1, order.getId());
            stm.setString(2, order.getDate());
            stm.setString(3, order.getCustomerId());

            if (stm.executeUpdate() > 0) {
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
                if (isOrderDetailAdd) {
                    boolean isUpdateStock = new ItemFormControlller().updateStock(order.getOrderDetails());
                    if (isUpdateStock) {
                        connection.commit();
                        return true;
                    }

                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
