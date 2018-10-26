package com.cristik.code.table;

import com.cristik.code.convert.Style;
import com.cristik.code.database.dialect.mysql.MysqlType;
import com.cristik.code.util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cristik
 */
public class ConvertFormat {

    private static final List<String> SQL_DATES = Arrays.asList("java.sql.Timestamp", "java.sql.Date", "java.sql.Time");
    private static final String UTIL_DATE_FULL_NAME = "java.util.Date";
    private static final String UTIL_DATE_NAME = "Date";

    private static final String DEFAULT_PACKAGE_PREFIX = "java.lang";

    private static final String BOOLEAN = DEFAULT_PACKAGE_PREFIX + ".Boolean";
    private static final String BOOLEAN_VALUE = "boolean";
    private static final String BOOLEAN_FIELD_PREFIX = "is";

    public static BeanDefine convert(TableInfo tableInfo) {
        //处理类名
        BeanDefine beanDefine = convertBean(tableInfo);
        //处理属性
        return convertFields(tableInfo, beanDefine);

    }

    /**
     * 将表字段处理成Java类的成员信息
     * 1.字段的Java类型
     * 2.Mybatis的JDBC类型
     * 3.是否是日期类
     * <p>
     * 4.变量名 驼峰首字母小写
     * 5.路径名 去除下划线全部小写
     * 6.方法名 驼峰首字母大写
     */
    private static BeanDefine convertFields(TableInfo tableInfo, BeanDefine beanDefine) {
        List<Field> fields = tableInfo.getColumns().stream().map(column -> {
            Field field = new Field();
            //字段对应表显示名称(去除前缀)
            field.setTable(tableInfo.getTable().getDisplayTableName());
            //数据库字段
            field.setColumn(column.getColumn());
            //数据库字段类型
            field.setDataType(column.getDataType());
            //数据库注释
            field.setComment(column.getComment());
            //是否是主键
            field.setKey(column.isKey());
            if (field.isKey()) {
                beanDefine.setKey(field);
            }
            String javaFullType = MysqlType.getJavaType(column.getDataType());
            if (SQL_DATES.stream().anyMatch(date -> date.equalsIgnoreCase(javaFullType))) {
                field.setJavaFullType(UTIL_DATE_FULL_NAME);
                field.setJavaType(UTIL_DATE_NAME);
                //判断是否是日期
                field.setDate(true);
            } else {
                //Java全限定名
                field.setJavaFullType(MysqlType.getJavaType(column.getDataType()));
                //Java类型名
                field.setJavaType(MysqlType.getJavaName(column.getDataType()));
                field.setDate(false);
            }
            //Mybatis类型
            field.setMybatisType(MysqlType.getMybatisType(column.getDataType()));
            //属性:驼峰
            field.setField(StringUtil.convertByStyle(column.getColumn(), Style.camel));
            //方法:驼峰大写开头
            field.setMethodName(StringUtil.convertByStyle(column.getColumn(), Style.capitalizeCamel));
            //处理是否是Boolean类型
            field.setBoolean(BOOLEAN_VALUE.equalsIgnoreCase(field.getJavaFullType())
                    || BOOLEAN.equalsIgnoreCase(field.getJavaFullType()));
            //处理Get和set方法布尔值特殊处理
            field.setSetMethod("set" + field.getMethodName());
            if (field.isBoolean()) {
                if (field.getMethodName().toLowerCase().startsWith(BOOLEAN_FIELD_PREFIX)) {
                    field.setSetMethod(StringUtil.convertByStyle(field.getMethodName(), Style.camel));
                } else {
                    field.setSetMethod("is" + field.getMethodName());
                }
            } else {
                field.setGetMethod("get" + field.getMethodName());
            }
            return field;
        }).collect(Collectors.toList());
        beanDefine.setFields(fields);
        beanDefine.setDependencies(convertDependency(fields));
        return beanDefine;
    }

    /**
     * 将表字段处理成Java类信息
     */
    private static BeanDefine convertBean(TableInfo tableInfo) {
        BeanDefine beanDefine = new BeanDefine();
        beanDefine.setTable(tableInfo.getTable().getDisplayTableName());
        beanDefine.setTableId(tableInfo.getTable().getTableName());

        //类名:驼峰大写开头
        beanDefine.setClassName(StringUtil.convertByStyle(beanDefine.getTable(), Style.capitalizeCamel));
        //对象名:驼峰
        beanDefine.setObjectName(StringUtil.convertByStyle(beanDefine.getTable(), Style.camel));
        //路径名:驼峰小写
        beanDefine.setPathName(StringUtil.convertByStyle(beanDefine.getTable(), Style.camel).toLowerCase());
        return beanDefine;
    }

    /**
     * 处理依赖对象
     */
    private static List<String> convertDependency(List<Field> fields) {
        List<String> dependencies = new ArrayList<>();
        for (Field field : fields) {
            if (isDefaultImport(field.getJavaFullType())) {
                continue;
            }
            dependencies.add(field.getJavaFullType());
        }
        return sortDuplicate(dependencies);
    }

    private static boolean isDefaultImport(String javaType) {
        if (javaType == null || javaType.length() == 0) {
            return true;
        }
        return javaType.startsWith(DEFAULT_PACKAGE_PREFIX);

    }

    /**
     * 去除重复元素并排序
     */
    private static List<String> sortDuplicate(List<String> list) {
        HashSet hashSet = new HashSet(list);
        list.clear();
        list.addAll(hashSet);
        Collections.sort(list);
        return list;
    }

}
