package com.cristik.code.model;

import com.cristik.code.table.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cristik
 */
public class Config {

    /**
     * Java类代码
     */
    private List<JavaModel> javaTemplates = new ArrayList<>();

    /**
     * Mapper配置
     */
    private List<MapperModel> mapperTemplates = new ArrayList<>();

    /**
     * Html生成代码
     */
    private List<HtmlModel> htmlTemplates = new ArrayList<>();

    /**
     * 配置的JDBC连接
     */
    private JdbcConnection jdbcConnection = new JdbcConnection();

    /**
     * 配置的数据库表
     */
    private List<Table> tables = new ArrayList<>();

    public List<JavaModel> getJavaTemplates() {
        return javaTemplates;
    }

    public List<MapperModel> getMapperTemplates() {
        return mapperTemplates;
    }

    public List<HtmlModel> getHtmlTemplates() {
        return htmlTemplates;
    }

    public List<Table> getTables() {
        return tables;
    }

    public JdbcConnection getJdbcConnection() {
        return jdbcConnection;
    }

}
