package Controller.Order;

import DBConnection.DBConnection;
import Model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {

    public boolean addOrderDetail(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            boolean isAddOrderDetail = addOrderDetail(orderDetail);
            if (!isAddOrderDetail) {
                return false;
            }
        }
        return true;
    }
    public boolean addOrderDetail(OrderDetail orderDetail) {
     String sql = "INSERT INTO orderdetail VALUES(?,?,?,?)";

        try {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,orderDetail.getOrderId());
            stm.setString(2,orderDetail.getItemCode());
            stm.setObject(3,orderDetail.getQty());
            stm.setObject(4,orderDetail.getUnitPrice());
            return stm.executeUpdate()>0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
