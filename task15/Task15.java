package task15;


import database.DAOListExpensesImpl;
import database.ListExpensesException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/** Задание 15. Добавить к созданному в предыдущем задании сервлету JSP и заменить сервлет таким образом,
 * чтобы работа с базой выполнялась сервлетом, а отображение результанов на странице - JSP.
 *
 */

public class Task15 extends HttpServlet {
    private static final long serialVersionUID=1L;
    // Объект класса для работы с базой данных
    private static DAOListExpensesImpl dao =DAOListExpensesImpl.getDAOListExpensesImpl();



    @Override
    public void init() {
        try {
            dao.getConnection();
        } catch (ListExpensesException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        ResultSet result;
        try {
            result =
                    dao.executeQueryAtListExpenses
                            ("select e.num,paydate,name,value from expenses as e,receivers as r where receiver=r.num;");
            ArrayList<ArrayList<String>> listOfResult = new ArrayList<>();
            while (result.next()) {
                ArrayList<String> line = new ArrayList<>();
                line.add(result.getString(1));
                line.add(result.getString(2));
                line.add(result.getString(3));
                line.add(result.getString(4));
                listOfResult.add(line);
            }
            //Рередаём в запрос информацию из базы данных
            request.setAttribute("result",listOfResult);

            //Передаём выполнение JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("task15.jsp");
            dispatcher.forward(request,response);


        } catch (ListExpensesException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException{
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