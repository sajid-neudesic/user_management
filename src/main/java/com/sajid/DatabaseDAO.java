package com.sajid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseDAO {
    @Autowired
    private Connection connection;

    public ResultSet readOnly(String sql) throws SQLException {     // return Result set on http request
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void updateOnly(String sql) throws SQLException {        // only does the operation
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(); //Or ps.execute() -> returns a boolean
    }
}
