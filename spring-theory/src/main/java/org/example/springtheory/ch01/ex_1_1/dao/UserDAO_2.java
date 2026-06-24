package org.example.springtheory.ch01.ex_1_1.dao;

import org.example.springtheory.ch01.ex_1_1.domain.User;

import java.sql.*;

public class UserDAO_2 {
    // 이 클래스가 로딩될 때 내부적으로 DriverManager에 자기 자신을 자동 등록한다.
    // → 등록이 되어야 아래 DriverManager.getConnection(...)으로 DB에 연결할 수 있다.
    // (참고: JDBC 4.0+ 부터는 자동 등록되어 생략 가능하지만, 동작 원리 이해를 위해 작성한다.)
    public void add(User user) throws ClassNotFoundException {
        String query = "INSERT INTO users(id, name, password) VALUES (?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ){
            pstmt.setString(1, user.getId());
            pstmt.setString(1, user.getName());
            pstmt.setString(1, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        String query = "SELECT * FROM users WHERE id = ?";



        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ){
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            resultSet.next();

            User user = new User();

            user.setId( resultSet.getString("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springtheory", "root", "20202020");

        return conn;
    }
}
