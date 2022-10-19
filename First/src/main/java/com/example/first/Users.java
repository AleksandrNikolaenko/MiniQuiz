package com.example.first;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Users extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        File[] users = new File("users\\").listFiles();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Users</title></head>");
        out.println("<body><h1>Users</h1><br>");
        for (File user : users) {
            String login = user.getName().substring(0, user.getName().indexOf('.'));
            if (!"admin.udb".equals(user.getName())) out.println("<a href=\"my_stats?user=" + login + "\">" + login + "</a><br>");
        }
        out.println("<br><form action=\"room.jsp\">");
        out.println("<button type=\"submit\">Back</button>");
        out.println("</form></body></html>");
    }
}
