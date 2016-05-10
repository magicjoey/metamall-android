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

/**
 * <p>.</p>
 *
 * @author DawnBells
 * @version RegisterServlet.java 1.0 Created@2016-05-10 19:39 $
 */
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
        PreparedStatement sql;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String msg = null;
        if (username != null && password != null) {
            msg = "success";

            try {
                // 加载Mysql驱动
                conn = JdbcConnect.DbConnect();

                String insertSql = "insert into user values(?,?)";
                sql = conn.prepareStatement(insertSql);
                sql.setString(1, username);
                sql.setString(2, password);
                int status = sql.executeUpdate();
                if (status != 0) {
                    System.out.println("插入数据成功");
                } else {
                    System.out.println("插入数据失败");
                }
                conn.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else {
            msg = "failed";
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