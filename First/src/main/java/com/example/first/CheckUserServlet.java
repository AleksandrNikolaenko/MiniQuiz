package com.example.first;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CheckUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (new File("users\\" + request.getParameter("login") + ".udb").exists()) {
            if (!checkPassword(request.getParameter("login"), request.getParameter("password"))) {
                doGet(request, response);
            } else {
                HttpSession session = request.getSession(true);
                session.setAttribute("user_login", request.getParameter("login"));
                response.sendRedirect("room.jsp");
            }
        } else {
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Error</title></head>");
        out.println("<h1>Incorrect login or password.<h1>");
        out.println("<form action=\"index.html\">");
        out.println("<button type=\"submit\">Back</button>");
        out.println("</form></body></html>");
    }

    private boolean checkPassword(String login, String password) throws IOException{
        try (Scanner scanner = new Scanner(new File("users\\" + login + ".udb"))) {
            String[] userArr = scanner.nextLine().split(" ");
            if (userArr[1].equals(password)) return true;
        }
        return false;
    }
}
