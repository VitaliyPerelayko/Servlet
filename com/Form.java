package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Form extends HttpServlet {

    private static final long serialVersionUID=1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Form</title></head>");
        out.println("<body><h1>Please enter the data</h1>");
        out.println("<form action=\"/a/MyServ\" method=\"GET\">");
        out.println("Name<br><input type=\"text\" name=\"Name\"><br>");
        out.println("E-mail<br><input type=\"text\" name=\"E-mail\"><br>");
        out.println("Phone<br><input type=\"text\" name=\"Phone\"><br>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
