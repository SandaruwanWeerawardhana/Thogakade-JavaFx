package DBConnection;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
    private static DBConnection Instance;
    @Getter
    private Connection connection;
    private final String url  = "jdbc:mysql://localhost:3306/thogakade";
    private final String userName  = "root";
    private final String password  = "1234";

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=DriverManager.getConnection(url,userName,password);
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (Instance==null){
            Instance = new DBConnection();
        }
        return Instance;
    }

}
