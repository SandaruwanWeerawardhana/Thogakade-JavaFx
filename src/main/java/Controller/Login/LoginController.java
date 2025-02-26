package Controller.Login;


import DBConnection.DBConnection;
import Model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public static Login check(String name) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select UserName,password from users where UserName=?");
        stm.setString(1, name);
//        stm.setString(2, password);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            return new Login(
                    resultSet.getString(2),
                    resultSet.getString(4)

            );
        }
        return null;
    }

}
