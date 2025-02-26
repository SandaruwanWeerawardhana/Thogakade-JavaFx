package Controller.Register;

import Util.CrudUtil;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.SQLException;

public class RegisterController {

    public static boolean add(String name, String email, String password) throws SQLException, ClassNotFoundException {
        String key = "1234";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String sql = "Insert into users (UserName,Email,Password) Values(?,?,?)";
        return CrudUtil.execute(sql,name,email,basicTextEncryptor.encrypt(password));
    }
}
