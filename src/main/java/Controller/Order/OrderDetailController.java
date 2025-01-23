package Controller.Order;

import DBConnection.DBConnection;
import Model.OrderDetail;
import Util.CrudUtil;

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
            return CrudUtil.execute(
                    orderDetail.getItemCode(),
                    orderDetail.getQty(),
                    orderDetail.getOrderId(),
                    orderDetail.getUnitPrice()
            );

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
