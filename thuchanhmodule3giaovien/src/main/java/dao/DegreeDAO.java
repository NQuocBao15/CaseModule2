package dao;

import model.Degree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DegreeDAO extends DatabaseConnection {
    public List<Degree> findAll(){
        List<Degree> results = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM degrees";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                results.add(new Degree(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return results;
    }

    public Degree findById(long id){
        String SELECT_BY_ID = "SELECT * FROM degrees WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new Degree(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }
}
