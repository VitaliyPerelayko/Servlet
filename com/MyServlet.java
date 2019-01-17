package com;

/**  Задание 8. Набрать приведённый пример, откомпилировать его, разместить на сервере
 *  (в т.ч. зарегистрировать в web.xml) и запустить из браузера.
 *
 *   Задание 9. Сделать простейший сервлет, подсчитывающийколичество посещений.
 *   Т.е. при каждом обращении к сервлету, следует увеличивать счётчикпосещений, и
 *   выводить его значение на страницу. Количество следует хранить в файле.
 *
 *   Задание 10. Сделать форму с вводом имени, телефона и электронной почты.
 *   Создать сервлет, который будет получать эти данные, и распечатывать на странице.
 *   Если пользователь не ввел имя, или одновременно пропущены телефон и электронная почта,
 *   следует выводить сообщение об ошибке.
 *
 *   Задание 11. Сделать сервлет, который по содержимому User-Agent будет определять с помощью какого
 *   браузера зашел пользователь, и выводить сообщение вида:
 *   Приветствую пользователя Firefox.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MyServlet extends HttpServlet {

    private static long countOfUsers;

    private static final long serialVersionUID=1L;
    //Путь к файлу, где храниться количество посещений (Задание 9)
    private static final String PATH="E:\\Работа\\Віталюга\\It-academi\\Servlet\\src\\main\\resources\\countOfUsers.dat";

    // При инициализации загружаем из файла счётчик посещений (Задание 9).
    public void init(){
        DataInputStream stream=null;
        try  {
            stream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(PATH)));
            countOfUsers=stream.readLong();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Получаем данные из формы (Задание 10)
        String name = request.getParameter("Name");
        String eMail = request.getParameter("E-mail");
        String phone = request.getParameter("Phone");
        // Проверка данных (Задание 10)
        boolean flag=(
                (
                        (name==null)|| ("".equals(name))
                )
                ||(
                        (
                                (eMail==null)||("".equals(eMail))
                        ) && (
                                (phone==null)||(("".equals(phone)))
                        )
                )
        );
        if (flag){
            //Перенаправляем запрос на другой сервлет, который сообщает об ошибке (Задание 10)
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Exception");
            dispatcher.forward(request,response);
        }else {
            // увеличиваем счётчик пользователей (Задание 9)
            countOfUsers++;
            // Определяем браузер (Задание 11)
            String browser = request.getHeader("User-Agent");
            PrintWriter out = response.getWriter();
            // Выводим информацию на странице
            out.println("<html><head><title>MyServ</title></head>");
            out.println("<body><h1>Hello " + name + ", You use the "+browser+"</h1>");
            out.println("<h1>You are " + countOfUsers + "th visitor</h1>");
            if (eMail!=null) {
                out.println("<h1>E-mail " + eMail + "</h1>");
            }
            if (phone!=null) {
                out.println("<h1>Phone " + phone + "</h1>");
            }
            out.println("</body></html>");
        }
    }

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    // При закрытии записываеи в файл счётчик посещений (Задание 9).
    public void destroy(){
        DataOutputStream stream=null;
        try  {
            stream = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(PATH)));
            stream.writeLong(countOfUsers);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
