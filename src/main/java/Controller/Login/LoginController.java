package Controller.Login;


import DBConnection.DBConnection;
import Model.Login;

import java.sql.*;

public class LoginController {

    public static Login check(String name, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Select * from users where username=? AND password=?");
        stm.setString(1, name);
        stm.setString(2, password);
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
