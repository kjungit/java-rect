package org.example.springtheory.springtheory.ch01.ex_1_3;

import org.example.springtheory.springtheory.ch01.ex_1_3.Dao.D_ConnectionMaker;
import org.example.springtheory.springtheory.ch01.ex_1_3.Dao.SimpleConnectionMaker;
import org.example.springtheory.springtheory.ch01.ex_1_3.Dao.UserDAO;

public class Start {
    public static void main(String[] args) {
        SimpleConnectionMaker conn = new D_ConnectionMaker();
        UserDAO userDAO = new UserDAO(conn);

    }
}
