package com.cristik.code.model;

import com.cristik.code.table.Field;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cristik
 */

@Data
@Accessors(chain = true)
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
}
