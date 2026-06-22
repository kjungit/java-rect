package org.example.springtheory.springtheory.ch01.ex_1_1;

import org.example.springtheory.springtheory.ch01.ex_1_1.dao.UserDAO;
import org.example.springtheory.springtheory.ch01.ex_1_1.domain.User;

public class Start {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();

        User user = new User();

        user.setId("test1");
        user.setName("testName1");
        user.setPassword("tessttest");

    }
}
