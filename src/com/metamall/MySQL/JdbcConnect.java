package com.metamall.MySQL;

/**
 * <p>.</p>
 *
 * @author DawnBells
 * @version JdbcConnect.java 1.0 Created@2016-05-09 4:17 $
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcConnect {

    public static Connection DbConnect() {
        Connection conn;
        String driver = "com.mysql.jdbc.Driver";
        String name = "root";
        String password = "pass";

        try {
            Class.forName(driver).newInstance();
            String url = "jdbc:mysql://localhost/userinfo";
            conn = DriverManager.getConnection(url, name, password);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static void DBClose(Connection conn) throws SQLException {
        conn.close();
    }
}
