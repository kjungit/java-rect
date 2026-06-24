package org.example.springtheory.ch01.ex_1_3.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class N_ConnectionMaker implements SimpleConnectionMaker {
    @Override
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springtheory", "root", "1234");

        return conn;
    }
}