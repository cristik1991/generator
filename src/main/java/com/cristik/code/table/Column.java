package com.cristik.code.table;

/**
 * @author cristik
 */
public class Column {

    /**
     * 是否是主键
     */
    private boolean key;

    /**
     * table名
     */
    private String table;

    /**
     * 数据库字段名
     */
    private String column;

    /**
     * 数据库注释
     */
    private String comment;

    /**
     * 数据库字段类型
     */
    private String dataType;

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
