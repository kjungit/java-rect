package com.management;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PricePlanDao extends BaseDao {
    private PricePlan toEnum(String name) {
        return PricePlan.from(name.toUpperCase());
    }

    public PricePlan findById(int id) {
        String query = "SELECT name FROM price_plan WHERE id = ?";

        return executeQuery(query, rs -> rs.next() ? toEnum(rs.getString("name")) : null, id);
    }

    public List<PricePlan> findAll() {
        String query = "SELECT name FROM price_plan";

        return executeQuery(query, rs -> {
            List<PricePlan> list = new ArrayList<>();
            while (rs.next()) {
                list.add(toEnum(rs.getString("name")));
            }
            return list;
        });
    }
}