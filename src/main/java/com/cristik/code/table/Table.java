package com.cristik.code.table;

/**
 * @author cristik
 * @description represent the table in config
 */
public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表名前缀
     */
    private String prefix;

    /**
     * 无前缀名
     */
    private String displayTableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDisplayTableName() {
        return displayTableName;
    }

    public void setDisplayTableName(String displayTableName) {
        this.displayTableName = displayTableName;
    }
}
