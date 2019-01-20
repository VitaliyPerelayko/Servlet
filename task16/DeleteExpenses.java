package task16;

import database.DAOListExpensesImpl;
import database.ListExpensesException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExpenses extends HttpServlet {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("deleteExpense.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException{
        String message="";
        String query = "delete from expenses where expenses.num="+request.getParameter("num")+";";
        try {
            boolean flag=dao.executeUpdateAtListExpenses(query);
            if (flag){
                message="Expense has been deleted";
            }else {
                message="Expense hasn't been deleted";
            }
        } catch (ListExpensesException e) {
            message+=e.toString();
            e.printStackTrace();
        }
        request.setAttribute("message",message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request,response);
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
