package jdbc;

import java.sql.*;

public class Sample {
    public static void main(String[] args) throws SQLException {

        // 有点疑惑的是没有添加mysql驱动
        String url = "jdbc:mysql://localhost:3306/authsys";
        String username = "root";
        String password = "1234";

        // try - with - resources
        try(Connection connection = DriverManager.getConnection(url,username,password)) {
            // 存在 SQL injection 问题
            try(Statement statement = connection.createStatement()) {
               try(ResultSet set = statement.executeQuery("")) {

               }
            }
            // preparedstmt 进行判断，拒绝SQL注入
            try (PreparedStatement pstmt = connection.prepareStatement("")){

            }
        }
    }
}
