package jdbc;

import java.sql.*;
import java.util.Properties;

public class Sample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/authsys";
        String username = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select config_id from sys_config limit 1");
            while(resultSet.next()) {
                int i = resultSet.getInt("config_id");
                System.out.println(i);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
