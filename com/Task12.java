package com;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Task12 extends HttpServlet {
    private static long countOfUsers;

    private static final long serialVersionUID=1L;
    //Путь к файлу, где храниться количество посещений
    private static final String PATH="E:\\Работа\\Віталюга\\It-academi\\Servlet\\src\\main\\resources\\countOfUsers.dat";

    // При инициализации загружаем из файла счётчик посещений
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

        // увеличиваем счётчик пользователей
        countOfUsers++;

        drawMessage("You are "+countOfUsers+"th visitor",response);

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    // При закрытии записываеи в файл счётчик посещений.
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

    public static void drawMessage(String message, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        BufferedImage image = new  BufferedImage(800,200,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Monospaced",Font.ITALIC,48));
        graphics.setColor(Color.YELLOW);
        graphics.drawString(message,100,100);

        ImageIO.write(image,"jpeg",response.getOutputStream());
    }

}
