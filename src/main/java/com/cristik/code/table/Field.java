package com.cristik.code.table;

/**
 * @author cristik
 */
public class Field extends Column {

    /**
     * java全限定名
     */
    private String javaFullType;

    /**
     * java类型名
     */
    private String javaType;

    /**
     * mybatisType类名
     */
    private String mybatisType;

    /**
     * 是否是日期类型
     */
    private boolean isDate;

    /**
     * 判断是否是Boolean类型
     */
    private boolean isBoolean;

    /**
     * 方法名 UserName
     */
    private String methodName;

    /**
     * Set方法名
     */
    private String setMethod;

    /**
     * get方法名
     */
    private String getMethod;

    /**
     * 变量名
     */
    private String field;

    /**
     * 页面id格式 user-name
     */
    private String fieldId;

    public String getJavaFullType() {
        return javaFullType;
    }

    public void setJavaFullType(String javaFullType) {
        this.javaFullType = javaFullType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getMybatisType() {
        return mybatisType;
    }

    public void setMybatisType(String mybatisType) {
        this.mybatisType = mybatisType;
    }

    public boolean isDate() {
        return isDate;
    }

    public void setDate(boolean date) {
        isDate = date;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSetMethod() {
        return setMethod;
    }

    public void setSetMethod(String setMethod) {
        this.setMethod = setMethod;
    }

    public String getGetMethod() {
        return getMethod;
    }

    public void setGetMethod(String getMethod) {
        this.getMethod = getMethod;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    public void setBoolean(boolean aBoolean) {
        isBoolean = aBoolean;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
}
