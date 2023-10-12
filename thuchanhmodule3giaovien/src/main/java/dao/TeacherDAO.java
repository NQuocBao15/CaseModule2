package dao;

import model.Degree;
import model.Teacher;
import model.enums.EGender;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDAO extends DatabaseConnection {
    private final String FIND_ALL_TEACHER = "SELECT t.*, d.name as degree_name FROM teachers t join degrees d on t.degree_id = d.id;";
    private final String INSERT_TEACHER = "INSERT INTO `teachers` (`name`, `dob`, `hobbie`, `gender`, `degree_id`) VALUES (?,?,?,?,?);";
    private final String UPDATE_TEACHER = "UPDATE `teachers` SET `name` = ?, `dob` = ?, `hobbie` = ?, `gender` = ?, `degree_id` = ? WHERE (`id` = ?);";
    private final String FIND_BY_ID = "SELECT t.*, d.name as degree_name FROM teachers t join degrees d on t.degree_id = d.id where t.id = ?";

    public List<Teacher> findAll() {
        List<Teacher> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TEACHER)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(getTeacherByResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void insertTeacher(Teacher teacher) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEACHER);

            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, String.valueOf(teacher.getDob()));
            preparedStatement.setString(3, teacher.getHobbie());
            preparedStatement.setString(4, String.valueOf(teacher.getGender()));
            preparedStatement.setString(5, String.valueOf(teacher.getDegree().getId()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTeacher(Teacher teacher, int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEACHER);

            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, String.valueOf(teacher.getDob()));
            preparedStatement.setString(3, teacher.getHobbie());
            preparedStatement.setString(4, String.valueOf(teacher.getGender()));
            preparedStatement.setString(5, String.valueOf(teacher.getDegree().getId()));
            preparedStatement.setInt(6,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Teacher findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return getTeacherByResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `teachers` where (`id`=?);")) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Teacher getTeacherByResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        LocalDate dob = rs.getDate("dob").toLocalDate();
        String hobbie = rs.getString("hobbie");
        EGender gender = EGender.valueOf(rs.getString("gender"));

        long degreeId = rs.getLong("degree_id");
        String degreeName = rs.getString("degree_name");
        Degree degree = new Degree(degreeId, degreeName);

        return new Teacher(id,name,dob,hobbie,gender,degree);
    }
}
