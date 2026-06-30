package org.example.springtheory.ch03.ex_3_1.dao;

import org.example.springtheory.ch03.ex_3_1.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// * 템플릿 메서드 패턴의 적용
// 상속을 통해 기능을 확장해서 사요하는 부분이다.
// 변하지 않는 부분은 슈퍼클래스에 두고 변하는 부분은 추상 메서드,로 정의해둬서
// 서브클래스에서 오버라이드하여 새롭게 정의해 쓰도록 하는 것이다.

public abstract class UserDAO {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }

    protected UserDAO() {}

    public void add(User user) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";

        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        }

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            resultSet.next();

            User user = new User();
            user.setId( resultSet.getString("id") );
            user.setName( resultSet.getString("name") );
            user.setPassword( resultSet.getString("password") );

            return user;
        }

    }

    public void deleteAll() throws SQLException, ClassNotFoundException {

        String query = "DELETE FROM users";

        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.executeUpdate();
        }
    }

    public int getCount() throws ClassNotFoundException, SQLException {
        String query = "SELECT COUNT(*) FROM users";

        try (
                Connection conn = simpleConnectionMaker.makeNewConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();
        ) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    protected abstract PreparedStatement makeStatement(Connection conn) throws ClassNotFoundException, SQLException;
}
