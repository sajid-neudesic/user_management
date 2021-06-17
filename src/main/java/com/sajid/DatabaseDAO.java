package com.sajid;

import java.sql.*;

public class DatabaseDAO {
    private Connection connection;

//    public DatabaseDAO() throws Exception {
//        this(DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db?useSSL=false", "root", "root"));
//    }

//    public DatabaseDAO(Connection connection) throws Exception {
//        this.connection = connection;
//    }             // doing this with bean

    public DatabaseDAO(Connection connection) {
        this.connection = connection;
    }

    public ResultSet readOnly(String sql) throws SQLException {     // return Result set on http request
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void updateOnly(String sql) throws SQLException {        // only does the operation
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(); //Or ps.execute() -> returns a boolean
    }
}
