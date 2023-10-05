package com.example.crudtest.dao;

import com.example.crudtest.model.Contact;
import com.example.crudtest.model.Group;
import com.example.crudtest.service.dto.PageableRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactDAO extends DatabaseConnection {
    private final String SELECT_ALL_CONTACT = "SELECT c.*, g.name as group_name FROM contacts c left join groupss g on c.group_id = g.id " +
            "where c.name like '%s' or c.mobile like '%s' or c.email like '%s' or c.company like '%s' or c.title like '%s' or g.name like '%s';";
    private final String INSERT_CONTACT = "INSERT INTO `contacts` (`name`, `mobile`, `email`, `company`, `title`, `group_id`, `avatar`) VALUES (?, ?, ?, ?, ?, ?,?);";
    private final String UPDATE_CONTACT = "UPDATE `contacts` SET `name` = ?, `mobile` = ?, `email` = ?, `company` = ?, `title` = ?, `group_id` = ?, `avatar` = ? WHERE (`id` = ?);";
    private final String FIND_BY_ID = "SELECT c.*, g.name as group_name FROM contacts c join groupss g on c.group_id = g.id where c.id = ?;";
    private final String FIND_ALL= "SELECT c.*, g.name as group_name FROM contacts c left join groupss g on c.group_id = g.id ";
    public List<Contact> getContact(){
        List<Contact> group = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(FIND_ALL)) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                group.add(getContactByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return group;
    }
    public List<Contact> findAll(PageableRequest request) {
        List<Contact> contacts = new ArrayList<>();
        String search = request.getSearch();
        if (search == null) {
            search = "%%";
        } else {
            search = "%" + search + "%";
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(String.format(SELECT_ALL_CONTACT,search,search,search,search,search,search))) {
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                contacts.add(getContactByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void insertContact(Contact contact) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT);

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getMobile());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getCompany());
            preparedStatement.setString(5, contact.getTitle());
            preparedStatement.setInt(6,contact.getGroups().getId());
            preparedStatement.setString(7, contact.getAvatar());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateContact(Contact contact) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTACT);

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getMobile());
            preparedStatement.setString(3, contact.getEmail());
            preparedStatement.setString(4, contact.getCompany());
            preparedStatement.setString(5, contact.getTitle());
            preparedStatement.setInt(6,contact.getGroups().getId());
            preparedStatement.setString(7, contact.getAvatar());
            preparedStatement.setInt(8,contact.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Contact> findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(getContactByResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void deleteById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `contacts` where (`id`=?);")) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Contact getContactByResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String mobile = rs.getString("mobile");
        String email = rs.getString("email");
        String company = rs.getString("company");
        String title = rs.getString("title");
        String avatar = rs.getString("avatar");

        int groupId = rs.getInt("group_id");
        String groupName = rs.getString("group_name");
        Group group = new Group(groupId,groupName);
        
        return new Contact(id,name,mobile,email,company,title,group,avatar);
    }
}