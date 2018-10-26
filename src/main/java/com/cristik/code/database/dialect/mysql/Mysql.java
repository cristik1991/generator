package com.cristik.code.database.dialect.mysql;

import com.cristik.code.database.AbstractJdbcConnection;
import com.cristik.code.database.DataBase;
import com.cristik.code.model.JdbcConnection;
import com.cristik.code.table.Column;
import com.cristik.code.table.TableInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cristik
 */
public class Mysql extends AbstractJdbcConnection implements DataBase {

    private static final String PRIMARY_KEY = "PRI";

    private static final String COLUMN_SQL = "SELECT TABLE_NAME,COLUMN_NAME ,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY FROM information_schema.`COLUMNS` WHERE TABLE_SCHEMA =?  AND TABLE_NAME =? ";

    private static final String TABLE_SQL = "SELECT TABLE_NAME,TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";


    @Override
    public TableInfo queryTableInfo(JdbcConnection jdbcConnection, String table) {
        Connection con = getConnection(jdbcConnection);
        TableInfo tableInfo = null;
        try {
            PreparedStatement pst = con.prepareStatement(TABLE_SQL);
            pst.setString(1, jdbcConnection.getSchema());
            pst.setString(2, table);
            ResultSet result = pst.executeQuery();

            while (result.next()) {
                tableInfo = new TableInfo();
                tableInfo.setTableName(result.getString("TABLE_NAME"));
                tableInfo.setTableComment(result.getString("TABLE_COMMENT"));
            }
            if (tableInfo != null) {
                tableInfo.setColumns(queryColumns(jdbcConnection, table));
            }
            closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfo;
    }

    private List<Column> queryColumns(JdbcConnection jdbcConnection, String table) {
        Connection con = getConnection(jdbcConnection);
        List<Column> columns = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement(COLUMN_SQL);
            pst.setString(1, jdbcConnection.getSchema());
            pst.setString(2, table);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Column column = new Column();
                column.setTable(result.getString("TABLE_NAME"));
                column.setColumn(result.getString("COLUMN_NAME"));
                column.setDataType(result.getString("DATA_TYPE"));
                column.setComment(result.getString("COLUMN_COMMENT"));
                column.setKey(isPrimaryKey(result.getString("COLUMN_KEY")));
                columns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(con);
        return columns;
    }


    @Override
    public boolean isPrimaryKey(String column) {
        if (column == null || column.length() == 0) {
            return false;
        }
        return PRIMARY_KEY.equalsIgnoreCase(column);
    }

}