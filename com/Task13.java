package com;

/** Задание 13. Изменить счётчик из задания 9 так, чтобы подсчитывались только уникальные посещения
 *  за сутки. Для контроля, был ли данный пользователь в течение последних суток на сайте использовать куки.
 *
 */


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Task13 extends Task12 {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //Проверка: был ли создан куки
        boolean flag=false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if ("wasHere".equals(cookie.getName())){
                flag=true;
                break;
            }
        }

        if (flag){
            Task12.drawMessage("You have already visited this site today",response);
        }else {
            // Если посещение уникальное, создаём куки
            Cookie c = new Cookie("wasHere","true");
            c.setMaxAge(60*60*24);
            response.addCookie(c);

            super.doGet(request,response);
        }

    }

}
