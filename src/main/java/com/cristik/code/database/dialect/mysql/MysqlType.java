package com.cristik.code.database.dialect.mysql;

import com.cristik.code.database.dialect.JDBCType;
import com.cristik.code.database.dialect.MybatisType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cristik
 */

public enum MysqlType {
    FLOAT(JDBCType.REAL, MybatisType.REAL),
    BLOB(JDBCType.LONGVARBINARY, MybatisType.LONGVARBINARY),
    SET(JDBCType.CHAR, MybatisType.CHAR),
    DECIMAL(JDBCType.DECIMAL, MybatisType.DECIMAL),
    CHAR(JDBCType.CHAR, MybatisType.CHAR),
    TEXT(JDBCType.LONGVARCHAR, MybatisType.LONGVARCHAR),
    MEDIUMTEXT(JDBCType.LONGVARCHAR, MybatisType.LONGVARCHAR),
    INT(JDBCType.INTEGER, MybatisType.INTEGER),
    YEAR(JDBCType.DATE, MybatisType.DATE),
    TIMESTAMP(JDBCType.TIMESTAMP, MybatisType.TIMESTAMP),
    DOUBLE(JDBCType.DOUBLE, MybatisType.DOUBLE),
    TINYTEXT(JDBCType.VARCHAR, MybatisType.VARCHAR),
    TINYINT(JDBCType.TINYINT, MybatisType.TINYINT),
    LONGBLOB(JDBCType.LONGVARBINARY, MybatisType.LONGVARBINARY),
    INTEGER(JDBCType.INTEGER, MybatisType.INTEGER),
    GEOMETRY(JDBCType.BINARY, MybatisType.BINARY),
    ENUM(JDBCType.CHAR, MybatisType.CHAR),
    NUMERIC(JDBCType.DECIMAL, MybatisType.DECIMAL),
    LONGTEXT(JDBCType.LONGVARCHAR, MybatisType.LONGVARCHAR),
    BIGINT(JDBCType.BIGINT, MybatisType.BIGINT),
    TIME(JDBCType.TIME, MybatisType.TIME),
    MEDIUMINT(JDBCType.INTEGER, MybatisType.INTEGER),
    BIT(JDBCType.BIT, MybatisType.BIT),
    DATE(JDBCType.DATE, MybatisType.DATE),
    DATETIME(JDBCType.TIMESTAMP, MybatisType.TIMESTAMP),
    TINYBLOB(JDBCType.BINARY, MybatisType.BINARY),
    MEDIUMBLOB(JDBCType.LONGVARBINARY, MybatisType.LONGVARBINARY),
    SMALLINT(JDBCType.SMALLINT, MybatisType.SMALLINT),
    INT24(JDBCType.INTEGER, MybatisType.INTEGER),
    REAL(JDBCType.DOUBLE, MybatisType.DOUBLE),
    VARCHAR(JDBCType.VARCHAR, MybatisType.VARCHAR);


    /**
     * 从Navicat上枚举的类型
     */
/*      TINYINT(JDBCType.CHAR, MybatisType.CHAR),
        SMALLINT(JDBCType.VARCHAR, MybatisType.CHAR),
        MEDIUMINT(JDBCType.LONGNVARCHAR, MybatisType.CHAR),
        INT(JDBCType.BOOLEAN, MybatisType.CHAR),
        INTEGER(JDBCType.BOOLEAN, MybatisType.CHAR),
        BIGINT(JDBCType.TINYINT, MybatisType.CHAR),
        BIT(JDBCType.SMALLINT, MybatisType.CHAR),
        REAL(JDBCType.BIGINT, MybatisType.CHAR),
        DOUBLE(JDBCType.INTEGER, MybatisType.CHAR),
        FLOAT(JDBCType.BIGINT, MybatisType.CHAR),
        decimal(JDBCType.BIGINT, MybatisType.CHAR),
        numeric(JDBCType.BIGINT, MybatisType.CHAR),
        CHAR(JDBCType.BIGINT, MybatisType.CHAR),
        varchar(JDBCType.BIGINT, MybatisType.CHAR),
        DATE(JDBCType.BIGINT, MybatisType.CHAR),
        TIME(JDBCType.BIGINT, MybatisType.CHAR),
        YEAR(JDBCType.BIGINT, MybatisType.CHAR),
        TIMESTAMP(JDBCType.BIGINT, MybatisType.CHAR),
        DATETIME(JDBCType.BIGINT, MybatisType.CHAR),
        TINYBLOB(JDBCType.BIGINT, MybatisType.CHAR),
        BLOB(JDBCType.BIGINT, MybatisType.CHAR),
        MEDIUMBLOB(JDBCType.BIGINT, MybatisType.CHAR),
        LONGBLOB(JDBCType.BIGINT, MybatisType.CHAR),
        TINYTEXT(JDBCType.BIGINT, MybatisType.CHAR),
        TEXT(JDBCType.BIGINT, MybatisType.CHAR),
        MEDIUMTEXT(JDBCType.BIGINT, MybatisType.CHAR),
        LONGTEXT(JDBCType.BIGINT, MybatisType.CHAR),
        ENUM(JDBCType.BIGINT, MybatisType.CHAR),
        SET(JDBCType.BIGINT, MybatisType.CHAR),
        BINARY(JDBCType.BIGINT, MybatisType.CHAR),
        VARBINARY(JDBCType.BIGINT, MybatisType.CHAR),
        POINT(JDBCType.BIGINT, MybatisType.CHAR),
        LINESTRING(JDBCType.BIGINT, MybatisType.CHAR),
        POLYGON(JDBCType.BIGINT, MybatisType.CHAR),
        GEOMETRY(JDBCType.BIGINT, MybatisType.CHAR),
        MULTIPOINT(JDBCType.BIGINT, MybatisType.CHAR),
        MULTILINESTRING(JDBCType.BIGINT, MybatisType.CHAR),
        MULTIPOLYGON(JDBCType.BIGINT, MybatisType.CHAR),
        GEOMETRYCOLLECTION(JDBCType.BIGINT, MybatisType.CHAR);*/

    public final JDBCType jdbcType;

    public final MybatisType myBatisType;

    private static Map<Integer, MysqlType> codeLookup = new HashMap<>();
    private static Map<String, MysqlType> nameLookup = new HashMap<>();

    static {
        for (MysqlType mysqlType : MysqlType.values()) {
            codeLookup.put(mysqlType.jdbcType.code, mysqlType);
            nameLookup.put(mysqlType.toString(), mysqlType);
        }
    }

    MysqlType(JDBCType jdbcType, MybatisType myBatisType) {
        this.jdbcType = jdbcType;
        this.myBatisType = myBatisType;
    }

    private static MysqlType getMysqlType(String name) {
        if (name == null) {
            return null;
        }
        return nameLookup.get(name.toUpperCase());
    }

    /**
     * java 全限定名
     */
    public static String getJavaType(String name) {
        MysqlType mysqlType = getMysqlType(name);
        if (mysqlType == null) {
            return null;
        }
        return JDBCType.getJavaType(mysqlType.jdbcType.code);
    }

    /**
     * java 类名
     */
    public static String getJavaName(String name) {
        MysqlType mysqlType = getMysqlType(name);
        if (mysqlType == null) {
            return null;
        }
        return JDBCType.getJavaName(mysqlType.jdbcType.code);
    }

    /**
     * mybatis的jdbcType
     */
    public static String getMybatisType(String name) {
        MysqlType mysqlType = getMysqlType(name);
        if (mysqlType == null) {
            return null;
        }
        return mysqlType.myBatisType.toString();
    }

}
