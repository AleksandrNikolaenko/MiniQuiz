package com.example.first;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class MyStats extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String login;
        if (request.getParameter("user") == null) login = (String) session.getAttribute("user_login");
        else login = request.getParameter("user");
        String[] userArr;
        try (Scanner scanner = new Scanner(new File("users\\" + login + ".udb"))) {
            userArr = scanner.nextLine().split(" ");
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (request.getParameter("user") == null) {
            out.println("<html><head><title>My Stats</title></head>");
            out.println("<body><h1>My stats</h1>");
        } else {
            out.println("<html><head><title>" + login + " stats</title></head>");
            out.println("<body><h1>" + login + " stats</h1>");
        }
        out.println("<h3>Form:</h3>");
        out.println("<p>Circle: " + userArr[2] + "<br>");
        out.println("Triangle: " + userArr[3] + "<br>");
        out.println("Square: " + userArr[4] + "</p>");
        out.println("<h3>Color:</h3>");
        out.println("<p>Red: " + userArr[5] + "<br>");
        out.println("Green: " + userArr[6] + "<br>");
        out.println("Blue: " + userArr[7] + "</p>");
        out.println("<h3>Size:</h3>");
        out.println("<p>Small: " + userArr[8] + "<br>");
        out.println("Medium: " + userArr[9] + "<br>");
        out.println("Large: " + userArr[10] + "</p><br><br>");
        if (request.getParameter("user") == null) out.println("<form action=\"room.jsp\">");
        else out.println("<form action=\"users\">");
        out.println("<button type=\"submit\">Back</button>");
        out.println("</form></body></html>");
        if (request.getParameter("user") != null) session.removeAttribute("user");
    }
}
