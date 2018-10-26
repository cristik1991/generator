package com.cristik.code.database;

import com.cristik.code.model.JdbcConnection;
import com.cristik.code.table.Column;
import com.cristik.code.table.TableInfo;

import java.util.List;

/**
 * @author cristik
 */
public interface DataBase {

    /**
     * 查询表信息返回所有字段
     * @param jdbcConnection
     * @param table
     * @return
     */
    TableInfo queryTableInfo(JdbcConnection jdbcConnection, String table);

    /**
     * 判断字段是否为主键
     * @param column
     * @return
     */
    boolean isPrimaryKey(String column);

}
