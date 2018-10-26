package com.cristik.code.database.dialect.sqlserver;

import com.cristik.code.database.DataBase;
import com.cristik.code.model.JdbcConnection;
import com.cristik.code.table.Column;
import com.cristik.code.table.TableInfo;

import java.util.List;

/**
 * @author cristik
 */
public class SqlServer implements DataBase {

    @Override
    public TableInfo queryTableInfo(JdbcConnection jdbcConnection, String table) {
        return null;
    }

    @Override
    public boolean isPrimaryKey(String column) {
        return false;
    }
}
