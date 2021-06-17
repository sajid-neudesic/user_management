package com.sajid;

/*

local
DirverManager.getConnection(jdbcUrl)
 */

import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    DatabaseDAO databaseDAO;

    public UserService(DatabaseDAO databaseDAO) {
        this.databaseDAO = databaseDAO;
    }

    public void add(User user) throws SQLException {
        add(user.getName(), user.getAge(), user.getMobileNumber(), user.getEmail());
    }

    public void add(String name, int age, int mobileNumber, String email) throws SQLException {     // Add new user
        try {
            String sql = "INSERT INTO user_info (Name, Age, Mobile_Number, Email) VALUES('" + name + "', '" + age + "', '" + mobileNumber + "', '" + email + "')";
            databaseDAO.updateOnly(sql);
        } catch (SQLException e) {
            System.out.println("User could not be added. Error is: " + e.getMessage());
        }
    }

    public void update(int id, User user) throws SQLException {     // update existing user of provided ID
        try {
            String sql = "UPDATE user_info SET Name = '"+user.getName()+"', Age = '"+user.getAge()+"', Mobile_Number = '"+user.getMobileNumber()+"', Email = '"+user.getEmail()+"' WHERE ID = '"+id+"' ";
            databaseDAO.updateOnly(sql);
        } catch (SQLException e) {
            System.out.println("User could not be updated. Error is: " + e.getMessage());
        }
    }

    public User searchWithEmail(String email) throws SQLException {     // provide details of user with given email
        try {
            String sql = "SELECT * FROM user_info WHERE Email = '" + email + "'";
            ResultSet resultSet = databaseDAO.readOnly(sql);
            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                int mobileNumber = resultSet.getInt(4);
                String emailID = resultSet.getString(5);
                return new User(id, name, age, mobileNumber, emailID);
            }
        } catch (SQLException e) {
            System.out.println("User not found");
        }

        return null;
    }

    public void delete(int id) throws SQLException {    // delete user with given id
        try {
            String sql = "DELETE FROM user_info WHERE ID =  '"+ id +"' ";
            databaseDAO.updateOnly(sql);
        } catch (SQLException e) {
            System.out.println("User could not be deleted: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() throws SQLException {       // list all users
        try {
            String sql = "SELECT * FROM user_info";
            ResultSet resultSet = databaseDAO.readOnly(sql);
            List<User> userList = new ArrayList<>();
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                int mobileNumber = resultSet.getInt(4);
                String email = resultSet.getString(5);

                userList.add(new User(id, name, age, mobileNumber, email));
            }
            return userList;
        } catch (SQLException e) {
            System.out.println("Users could not be retrieved: " + e.getMessage());
        }

        return null;
    }
}
