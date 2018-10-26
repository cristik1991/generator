package com.cristik.code.database.dialect;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis 与 jdbc类型关联
 * @author cristik
 */
public enum MybatisType {

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
    // Oracle
    CURSOR(-10),
    UNDEFINED(Integer.MIN_VALUE + 1000),
    // JDK6
    NVARCHAR(Types.NVARCHAR),
    // JDK6
    NCHAR(Types.NCHAR),
    // JDK6
    NCLOB(Types.NCLOB),
    STRUCT(Types.STRUCT);

    public final int code;

    private static Map<Integer, MybatisType> codeLookup = new HashMap<>();
    private static Map<String, MybatisType> nameLookup = new HashMap<>();

    static {
        for (MybatisType type : MybatisType.values()) {
            codeLookup.put(type.code, type);
            nameLookup.put(type.toString(), type);
        }
    }

    MybatisType(int code) {
        this.code = code;
    }

    private static MybatisType getMybatisType(int code) {
        return codeLookup.get(code);
    }

    private static MybatisType getMybatisType(String name) {
        return codeLookup.get(name);
    }

    /**
     * 根据JdbcType code获取java全限定名
     */
    public static String getJavaType(int code) {
        MybatisType mybatisType = getMybatisType(code);
        if (mybatisType == null) {
            return null;
        }
        return JDBCType.getJavaType(mybatisType.code);
    }

    /**
     * 根据Mybatis的JdbcType名获取java全限定名
     */
    public static String getJavaType(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        MybatisType mybatisType = getMybatisType(name.toUpperCase());
        if (mybatisType == null) {
            return null;
        }
        return JDBCType.getJavaType(mybatisType.code);
    }

}
