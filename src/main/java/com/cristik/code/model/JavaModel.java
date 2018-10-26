package com.cristik.code.model;

import com.cristik.code.convert.Layer;
import com.cristik.code.table.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cristik
 */
public class JavaModel extends Model {

    /**
     * 依赖列表
     */
    private List<String> importList = new ArrayList<>();

    /**
     * 对象名
     */
    private String objectName;

    /**
     * 类名
     */
    private String className;

    /**
     * 属性列表
     */
    private List<Field> fields;

    public List<String> getImportList() {
        return importList;
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

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
