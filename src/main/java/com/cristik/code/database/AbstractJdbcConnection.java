package com.cristik.code.database;

import com.cristik.code.model.JdbcConnection;
import com.cristik.code.table.Column;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author cristik
 */
public abstract class AbstractJdbcConnection {

    public static Connection getConnection(JdbcConnection jdbcConnection) {
        Connection con = null;
        try {
            Class.forName(jdbcConnection.getDriverName());
            con = DriverManager.getConnection(jdbcConnection.getUrl()
                    , jdbcConnection.getUserName()
                    , jdbcConnection.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }


    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
