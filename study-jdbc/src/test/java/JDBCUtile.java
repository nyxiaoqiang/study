import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class JDBCUtile {
    private static Properties properties;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() throws SQLException {
        String url = "jdbc:mysql://192.168.253.111:3306/study";
        String user = "root";
        String pwd = "root";
        return DriverManager.getConnection(url, user, pwd);
    }

    public static void free(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
