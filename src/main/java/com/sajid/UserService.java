package com.sajid;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers() throws SQLException;

    public User getUserById(int id);

    public void addUser(User user) throws SQLException;

    public void deleteUser(int id) throws SQLException;

    public void updateUser(int id, User user) throws SQLException;
}
