package org.example.springtheory.ch02;

import org.example.springtheory.ch02.dao.DaoFactory;
import org.example.springtheory.ch02.dao.UserDAO;
import org.example.springtheory.ch02.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Start {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
        User user = userDAO.get("test1");
        System.out.println("test2".equals(user.getName()));
    }
}
