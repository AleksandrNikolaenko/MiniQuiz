package com.example.first;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProcessQuiz extends HttpServlet {

    private boolean validQuiz;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        validQuiz = true;
        String form = request.getParameter("form");
        String color = request.getParameter("color");
        String size = request.getParameter("amount");
        if (form == null || color == null || size == null) {
            validQuiz = false;
            doGet(request, response);
        } else {
            HttpSession session = request.getSession(true);
            String login = (String) session.getAttribute("user_login");
            String[] userArr;
            try (Scanner scanner = new Scanner(new File("users\\" + login + ".udb"))) {
                userArr = scanner.nextLine().split(" ");
            }
            User user = new User(userArr[0], userArr[1], getAnswers(userArr));
            user.setUser(getQuizResult(form, color, size));
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if (validQuiz) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>OK</title></head><body>");
            out.println("<h1>Thank you. Your answers have been accepted.</h1><br>");
            out.println("<form action=\"room.jsp\">");
            out.println("<button type=\"submit\">Ok</button>");
            out.println("</form></body></html>");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>error</title></head><body>");
            out.println("<h1>Rejected. Answer all questions.</h1><br>");
            out.println("<form action=\"quiz.html\">");
            out.println("<button type=\"submit\">Back</button>");
            out.println("</form></body></html>");
        }
    }

    private int[] getAnswers(String[] userArr) {
        int[] answers = new int[userArr.length - 2];
        for (int i = 0; i < userArr.length - 2; i++) {
            answers[i] = Integer.parseInt(userArr[i + 2]);
        }
        return answers;
    }

    private boolean[] getQuizResult(String form, String color, String size) {
        return new boolean[] {"circle".equals(form), "triangle".equals(form), "square".equals(form),
                            "red".equals(color), "green".equals(color), "blue".equals(color),
                            "small".equals(size), "medium".equals(size), "large".equals(size)};
    }
}
