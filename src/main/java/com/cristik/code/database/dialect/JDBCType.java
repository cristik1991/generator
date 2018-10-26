package com.cristik.code.database.dialect;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cristik
 */
public enum JDBCType {
    /**
     * 确定类型
     **/
    BIGINT(Types.BIGINT, Long.class),
    BINARY(Types.BINARY, Byte[].class),
    BIT(Types.BIT, Boolean.class),
    BLOB(Types.BLOB, Byte[].class),
    CHAR(Types.CHAR, String.class),
    CLOB(Types.CLOB, String.class),
    DATE(Types.DATE, Date.class),
    DECIMAL(Types.DECIMAL, BigDecimal.class),
    DOUBLE(Types.DOUBLE, Double.class),
    FLOAT(Types.FLOAT, Double.class),
    INTEGER(Types.INTEGER, Integer.class),
    JAVA_OBJECT(Types.JAVA_OBJECT, Object.class),
    LONGVARBINARY(Types.LONGVARBINARY, Byte[].class),
    LONGVARCHAR(Types.LONGVARCHAR, String.class),
    NUMERIC(Types.NUMERIC, BigDecimal.class),
    OTHER(Types.OTHER, Object.class),
    REAL(Types.REAL, Float.class),
    SMALLINT(Types.SMALLINT, Integer.class),
    TIME(Types.TIME, Time.class),
    TIMESTAMP(Types.TIMESTAMP, Timestamp.class),
    TINYINT(Types.TINYINT, Boolean.class),
    VARBINARY(Types.VARBINARY, Byte[].class),
    VARCHAR(Types.VARCHAR, String.class),


    ARRAY(Types.ARRAY, Array.class),
    BOOLEAN(Types.BOOLEAN, Boolean.class),

    /**
     * 未定义
     **/
    DISTINCT(Types.DISTINCT, Object.class),
    DATALINK(Types.DATALINK, Object.class),
    LONGNVARCHAR(Types.LONGNVARCHAR, Object.class),
    NCHAR(Types.NCHAR, Object.class),
    NCLOB(Types.NCLOB, Object.class),
    NULL(Types.NULL, Object.class),
    NVARCHAR(Types.NVARCHAR, Object.class),
    REF(Types.REF, Object.class),
    REF_CURSOR(Types.REF_CURSOR, Object.class),
    ROWID(Types.ROWID, Object.class),
    STRUCT(Types.STRUCT, Object.class),
    SQLXML(Types.SQLXML, Object.class),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, Object.class),
    TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE, Object.class);

    //JDBC code
    public final int code;
    //Java 类型
    public final Class javaClass;

    private static Map<Integer, JDBCType> codeLookup = new HashMap<>();
    private static Map<String, JDBCType> nameLookup = new HashMap<>();

    static {
        for (JDBCType type : JDBCType.values()) {
            codeLookup.put(type.code, type);
            nameLookup.put(type.toString(), type);
        }
    }

    JDBCType(int code, Class javaClass) {
        this.code = code;
        this.javaClass = javaClass;
    }

    private static JDBCType getJDBCType(int code) {
        return codeLookup.get(code);
    }

    private static JDBCType getJDBCType(String name) {
        if (name == null) {
            return null;
        }
        return nameLookup.get(name.toUpperCase());
    }


    /**
     * 根据JdbcType 名称获取Java全限定名
     */
    public static String getJavaType(String name) {
        JDBCType jdbcType = getJDBCType(name.toUpperCase());
        if (jdbcType == null) {
            return null;
        }
        return jdbcType.getClass().getName();
    }

    /**
     * 根据JdbcType Code获取Java全限定名
     */
    public static String getJavaType(int code) {
        JDBCType jdbcType = getJDBCType(code);
        if (jdbcType == null) {
            return null;
        }
        return jdbcType.javaClass.getName();
    }

    /**
     * 根据JdbcType Code获取Java类名
     */
    public static String getJavaName(int code) {
        JDBCType jdbcType = getJDBCType(code);
        if (jdbcType == null) {
            return null;
        }
        String javaType = jdbcType.javaClass.getName();
        // TODO: 2018/1/24 处理数组类型
        return javaType.substring(javaType.lastIndexOf(".")+1);
    }
}


