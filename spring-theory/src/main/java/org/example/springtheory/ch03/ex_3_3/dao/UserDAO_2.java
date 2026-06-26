package org.example.springtheory.ch03.ex_3_3.dao;

import org.example.springtheory.ch03.ex_3_3.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO_2 {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDAO_2(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }

    protected UserDAO_2() {}

    public void jdbcContextWithStatementStrategy(StatementStrategy statementStrategy) throws SQLException, ClassNotFoundException {
        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = statementStrategy.makeStatement(conn); // 변하는 부분을 전략에 위임
        ) {
            pstmt.executeUpdate();
        }
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection conn) throws SQLException {
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO users(id, name, password) VALUES(?, ?, ?)"
                );

                pstmt.setString(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getPassword());

                return pstmt;
            }
        };


        jdbcContextWithStatementStrategy(statementStrategy);
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {

        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makeStatement(Connection conn) throws SQLException {
                return conn.prepareStatement("DELETE FROM users");
            }
        };

        jdbcContextWithStatementStrategy(statementStrategy);
    }
}
