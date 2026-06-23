package org.example.springtheory.springtheory.ch01.ex_1_6;


import org.example.springtheory.springtheory.ch01.ex_1_6.dao.DaoFactory;
import org.example.springtheory.springtheory.ch01.ex_1_6.dao.UserDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Start {
    static void main(String[] args) throws SQLException, ClassNotFoundException {
        DaoFactory factory = new DaoFactory();

        UserDAO useredDAO1 = factory.userDAO();
        UserDAO useredDAO2 = factory.userDAO();

        System.out.println(useredDAO1);
        System.out.println(useredDAO2);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDAO useredDAO3 = context.getBean("userDAO", UserDAO.class);
        UserDAO useredDAO4 = context.getBean("userDAO", UserDAO.class);

        System.out.println(useredDAO3);
        System.out.println(useredDAO4);
    }
}










