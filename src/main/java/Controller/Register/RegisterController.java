package Controller.Register;

import DBConnection.DBConnection;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    public static boolean add(String name,String email, String password) throws SQLException, ClassNotFoundException {
        String key = "1234";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into users Values(?,?,?,?)");
        stm.setInt(1,0);
        stm.setString(2, name);
        stm.setString(3, email);
        stm.setString(4, basicTextEncryptor.encrypt(password));
        return stm.executeUpdate()>0;
    }
}
