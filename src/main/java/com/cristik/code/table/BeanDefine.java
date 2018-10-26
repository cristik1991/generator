package com.cristik.code.table;

import java.util.List;

/**
 * @author cristik
 */
public class BeanDefine {

    /**
     * 去除前缀后的表名
     */
    String table;

    /**
     * 对象名
     */
    String objectName;

    /**
     * 类名
     */
    String className;

    /**
     * 路径名
     */
    String pathName;

    /**
     * 数据库表名
     */
    String tableId;

    Field key;

    List<Field> fields;

    List<String> dependencies;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Field getKey() {
        return key;
    }

    public void setKey(Field key) {
        this.key = key;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }
}
