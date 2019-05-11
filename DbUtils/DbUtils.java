package DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtils {

    static String USERNAME = "root";
    static String PASS = "p@n0shd1953Oh00ji3";
    static String MYSQLURL = "jdbc:mysql://localhost:3306/dblyrics";


    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(MYSQLURL, USERNAME, PASS);
            if (con == null) {
                System.out.println("connection not established");
            } else {
                System.out.println("Database connection established");
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closePStatement(PreparedStatement preparedStatement){
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}