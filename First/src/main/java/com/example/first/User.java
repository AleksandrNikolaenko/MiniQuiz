package com.example.first;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class User {

    private final String login;
    private final String password;
    private int[] answers;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.answers = new int[9];
    }

    public User(String login, String password, int[] answers){
        this.login = login;
        this.password = password;
        this.answers = answers;
    }

    public void saveUser() throws IOException {
        File user = new File("users\\" + login + ".udb");
        try(PrintWriter out = new PrintWriter(user)) {
            out.print(login + " " + password + " ");
            for (int answer : answers) {
                out.print(answer + " ");
            }
        }
    }
    public void setUser(boolean[] quizResult) throws IOException{
        for (int i = 0; i < quizResult.length; i++) {
            if (quizResult[i]) answers[i]++;
        }
        saveUser();
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                Arrays.toString(answers) +
                '}';
    }
}
