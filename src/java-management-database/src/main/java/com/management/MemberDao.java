package com.management;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends BaseDao{
    public boolean insert(Member member) {
        String query = "INSERT INTO member (name, email, phone, grade, price_plan_id) VALUES (?, ?, ?, ?, ?)";
        return executeUpdate(query, member.getName(), member.getEmail(), member.getPhone(), member.getGrade().name(), member.getPricePlanId());
    }

    public boolean update(String targetEmail, Member member) {
        String query = "UPDATE member SET name = ?, email = ?, phone = ?, grade = ? WHERE email = ?";
        return executeUpdate(query, member.getName(), member.getEmail(), member.getPhone(), member.getGrade().name(), targetEmail);
    }


    public boolean delete(String email) {
        String query = "DELETE FROM member WHERE email = ?";
        return executeUpdate(query, email);
    }

    public Member findByEmail(String email, int pricePlanId) {
        String query = "SELECT * FROM member WHERE email = ? AND price_plan_id = ?";
        return executeQuery(query, rs -> rs.next() ? createMember(rs) : null, email, pricePlanId);
    }

    public Member findByName(String name, int pricePlanId) {
        String query = "SELECT * FROM member WHERE name = ? AND price_plan_id = ?";
        return executeQuery(query, rs -> rs.next() ? createMember(rs) : null, name, pricePlanId);
    }

    public List<Member> findAll(int pricePlanId) {
        String query = "SELECT * FROM member WHERE price_plan_id = ?";
        return executeQuery(query, rs -> {
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                members.add(createMember(rs) );
            }
            return members;
        }, pricePlanId);
    }

    public int count(int pricePlanId) {
        String query = "SELECT COUNT(*) FROM member WHERE price_plan_id = ?";
        return executeQuery(query, rs -> rs.next() ? rs.getInt(1) : 0, pricePlanId);
    }

    private Member createMember(ResultSet rs) throws SQLException {
        return new Member(
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("phone"),
            Grade.valueOf(rs.getString("grade")),
            rs.getInt("price_plan_id"));
    }
}
