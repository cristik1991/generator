package com.cristik.code.table;

import java.util.List;

/**
 * @author cristik
 */
public class TableInfo {

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 表名注释
     */
    private String tableComment;

    /**
     * 表字段信息
     */
    private List<Column> columns;


    /**
     * 表配置信息
     */
    private Table table;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
