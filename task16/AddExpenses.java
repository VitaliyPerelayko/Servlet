package task16;

import database.DAOListExpensesImpl;
import database.Expense;
import database.ListExpensesException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddExpenses extends HttpServlet {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("addExpenses.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException{
        String message="";
        Expense expense = new Expense();
        String[] date = request.getParameter("paydate").split("-");
        expense.setPaydate(LocalDate.of
                (Integer.getInteger(date[0]),Integer.getInteger(date[1]),Integer.getInteger(date[2])));
        expense.setReseiver(Integer.getInteger(request.getParameter("receiver")));
        expense.setValue(new BigDecimal(request.getParameter("value")));
        try {
            int num = dao.addExpense(expense);
            if (num==-1){
                message="Your expense has been added with the number: "+num;
            }else {
                message="Expense hasn't been added";
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
