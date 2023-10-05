package com.example.crudtest.dao;

import com.example.crudtest.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends DatabaseConnection{
    private static final String SELECT_ALL_GROUP = "SELECT * FROM groupss;";

    public List<Group> getGroup(){
        List<Group> group = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(SELECT_ALL_GROUP)) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                group.add(new Group(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }
}
