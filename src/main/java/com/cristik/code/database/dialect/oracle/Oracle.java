package com.cristik.code.database.dialect.oracle;

import com.cristik.code.database.DataBase;
import com.cristik.code.model.JdbcConnection;
import com.cristik.code.table.Column;
import com.cristik.code.table.TableInfo;

import java.sql.Types;
import java.util.List;

/**
 * @author cristik
 */
public class Oracle implements DataBase {


    @Override
    public TableInfo queryTableInfo(JdbcConnection jdbcConnection, String table) {
        return null;
    }

    @Override
    public boolean isPrimaryKey(String column) {
        return false;
    }

    enum JdbcTye {
        ARRAY(Types.ARRAY),
        BIT(Types.BIT),
        TINYINT(Types.TINYINT),
        SMALLINT(Types.SMALLINT),
        INTEGER(Types.INTEGER),
        BIGINT(Types.BIGINT),
        FLOAT(Types.FLOAT),
        REAL(Types.REAL),
        DOUBLE(Types.DOUBLE),
        NUMERIC(Types.NUMERIC),
        DECIMAL(Types.DECIMAL),
        CHAR(Types.CHAR),
        VARCHAR(Types.VARCHAR),
        LONGVARCHAR(Types.LONGVARCHAR),
        DATE(Types.DATE),
        TIME(Types.TIME),
        TIMESTAMP(Types.TIMESTAMP),
        BINARY(Types.BINARY),
        VARBINARY(Types.VARBINARY),
        LONGVARBINARY(Types.LONGVARBINARY),
        NULL(Types.NULL),
        OTHER(Types.OTHER),
        BLOB(Types.BLOB),
        CLOB(Types.CLOB),
        BOOLEAN(Types.BOOLEAN),
        CURSOR(-10), // Oracle
        UNDEFINED(Integer.MIN_VALUE + 1000),
        NVARCHAR(Types.NVARCHAR), // JDK6
        NCHAR(Types.NCHAR), // JDK6
        NCLOB(Types.NCLOB), // JDK6
        STRUCT(Types.STRUCT);

        JdbcTye(int array) {
        }
    }

}
