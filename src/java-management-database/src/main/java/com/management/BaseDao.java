package com.management;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class BaseDao {
    private static final Dotenv dotenv = Dotenv.load();

    private final String url = dotenv.get("DB_URL");
    private final String user = dotenv.get("DB_USER");
    private final String password = dotenv.get("DB_PASSWORD");

    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean executeUpdate(String query, Object... params) {
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @FunctionalInterface
    interface ResultSetRowMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    <T> T executeQuery(String query, ResultSetRowMapper<T> mapper, Object... params) {
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                return mapper.map(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
