package com.cristik.code.convert;

/**
 * @author cristik
 */
public enum DataType {
    /*整数*/
    INT("Integer", "java.lang.Integer", "INTEGER"),
    SMALLINT("Short", "java.lang.Short", "SMALLINT"),
    BIGINT("Long", "java.lang.Long", "BIGINT"),
    TINYINT("Byte", "java.lang.Byte", "TINYINT"),
    /*小数*/
    DECIMAL("Integer", "java.lang.Integer", "INTEGER"),
    DOUBLE("Double", "java.lang.Double", "DOUBLE"),
    FLOAT("Float", "java.lang.Float", "REAL"),
    /*字符串*/
    LONGTEXT("String", "java.lang.String", "LONGVARCHAR"),
    TEXT("String", "java.lang.String", "LONGVARCHAR"),
    CHAR("String", "java.lang.String", "CHAR"),
    VARCHAR("String", "java.lang.String", "VARCHAR"),
    /*日期类型*/
    DATE("Date", "java.util.Date", "DATE"),
    DATETIME("Date", "java.util.Date", "TIMESTAMP"),
    TIME("Date", "java.util.Date", "TIME"),
    TIMESTAMP("Date", "java.util.Date", "TIMESTAMP"),
    /*二进制*/
    LONGBLOB("byte[]", "java.lang.Byte", "LONGVARBINARY"),;

    private String javaType;

    private String fullTypeName;

    private String jdbcType;

    DataType(String javaType, String fullTypeName, String jdbcType) {
        this.javaType = javaType;
        this.fullTypeName = fullTypeName;
        this.jdbcType = jdbcType;
    }

    public String getFullTypeName() {
        return fullTypeName;
    }

    public void setFullTypeName(String fullTypeName) {
        this.fullTypeName = fullTypeName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
