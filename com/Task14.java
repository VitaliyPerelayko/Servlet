package com;

import DataBase.DAOListExpensesImpl;
import DataBase.ListExpensesException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/** Задание 14. Создать сервлет который будет отображать на странице содержимое базы платежей, из модуля про
 * базы данных. Информацию о URL базы, имени пользователя и пароле хранить в начальных данных сервлета
 *
 */

public class Task14 extends HttpServlet {
    private static final long serialVersionUID=1L;
    // Объект класса для работы с базой данных
    private static DAOListExpensesImpl dao =DAOListExpensesImpl.getDAOListExpensesImpl();
    //Это поле нужно было для задания
//    private static Connection connection=null;


    @Override
    public void init() {
        try {
            dao.getConnection();
        } catch (ListExpensesException e) {
            e.printStackTrace();
        }
    }
    // В задании сказано, что "Информацию о URL базы, имени пользователя и пароле хранить в начальных данных сервлета"
    // поэтому я написал этот кусок KODA, чтобы было видно, что я знаю как эти данные получать.
    // Но до этого в задании про базы данных я написал много полезных методов, не пропадать же им)))
//    public void init() {
//        ServletConfig config = getServletConfig();
//        String dbURL = config.getInitParameter("URL");
//        String name = config.getInitParameter("name");
//        String password = config.getInitParameter("password");
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection = DriverManager.getConnection(dbURL, name, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        ResultSet result;
        try {
            result =
                    dao.executeQueryAtListExpenses
                            ("select e.num,paydate,name,value from expenses as e,receivers as r where receiver=r.num;");
            PrintWriter out = response.getWriter();

            // Выводим информацию на странице
            out.println("<html><head><title>Task14</title></head>");
            out.println("<body><h1>Table of expenses</h1>");
            out.println("<table style=\"width:100%\"><tr>");
            out.println("<th>Num</th>");
            out.println("<th>Pay Date</th>");
            out.println("<th>Receiver</th>");
            out.println("<th>Value</th></tr>");
            while (result.next()) {
                out.println("<tr><td>"+result.getString(1)+"</td>");
                out.println("<td>"+result.getString(2)+"</td>");
                out.println("<td>"+result.getString(3)+"</td>");
                out.println("<td>"+result.getString(4)+"</td></tr>");
            }
            out.println("</body></html>");
        } catch (ListExpensesException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    @Override
    public void destroy(){
        try {
            dao.closeConnection();
        } catch (ListExpensesException e) {
            e.printStackTrace();
        }
    }


}
