package com.example.first;

import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!new File("users").exists()) new File("users").mkdir();
        if (new File("users\\"+ request.getParameter("login") + ".udb").exists()) {
            doGet(request, response);
        } else {
            User newUser = new User(request.getParameter("login"), request.getParameter("password"));
            newUser.saveUser();
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", request.getParameter("login"));
            response.sendRedirect("room.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>exist login</title></head><body>");
        out.println("<h1>This login already exists</h1><br>");
        out.println("<form action=\"registration.html\">");
        out.println("<button type=\"submit\">Back</button>");
        out.println("</form></body></html>");
    }
}
