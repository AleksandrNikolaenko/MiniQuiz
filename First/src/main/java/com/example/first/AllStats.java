package com.example.first;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class AllStats extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int[] answerArr = getAnswerArr();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>All Stats</title></head>");
        out.println("<body><h1>All stats</h1>");
        out.println("<h3>Form:</h3>");
        out.println("<p>Circle: " + answerArr[0] + "<br>");
        out.println("Triangle: " + answerArr[1] + "<br>");
        out.println("Square: " + answerArr[2] + "</p>");
        out.println("<h3>Color:</h3>");
        out.println("<p>Red: " + answerArr[3] + "<br>");
        out.println("Green: " + answerArr[4] + "<br>");
        out.println("Blue: " + answerArr[5] + "</p>");
        out.println("<h3>Size:</h3>");
        out.println("<p>Small: " + answerArr[6] + "<br>");
        out.println("Medium: " + answerArr[7] + "<br>");
        out.println("Large: " + answerArr[8] + "</p><br><br>");
        out.println("<form action=\"room.jsp\">");
        out.println("<button type=\"submit\">Back</button>");
        out.println("</form></body></html>");
    }

    private int[] getAnswerArr() throws IOException{
        int[] answerArr = new int[9];
        File[] users = new File("users\\").listFiles();
        for (File user : users) {
            if (!"admin.udb".equals(user.getName())) {
                try (Scanner scanner = new Scanner(user)) {
                    String[] userArr = scanner.nextLine().split(" ");
                    for (int i = 0; i < answerArr.length; i++) {
                        answerArr[i] += Integer.parseInt(userArr[i + 2]);
                    }
                }
            }
        }
        return answerArr;
    }
}
