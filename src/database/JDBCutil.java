package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCutil {

    public static Connection getConnection(){
        Connection cnt = null;

        try {
//            DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BookStoreDB";
            String username = "root";
            String password = "260804";
            cnt = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("e");
        }
        return cnt;
    }

    public static void closeConection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("e");
        }
    }
}
