package com.metamall.LoginRegister;

import com.metamall.MySQL.JdbcConnect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p>.</p>
 *
 * @author DawnBells
 * @version LoginServlet.java 1.0 Created@2016-05-10 19:30 $
 */
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn;
        PreparedStatement sql;
        ResultSet rs;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String msg = null;

        String selectsql = "select username,password from user where username=? and password=?";

        try {
            conn = JdbcConnect.DbConnect();
            sql = conn.prepareStatement(selectsql);

            if (username != null && password != null) {
                sql.setString(1, username);
                sql.setString(2, password);
                rs = sql.executeQuery();
                boolean bool = rs.next();
                if (bool == true) {
                    msg = "success";
                } else {
                    msg = "failed";
                }
            } else {
                msg = "failed";
            }

            conn.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        out.print(msg);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}