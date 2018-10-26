package com.cristik.code.build;

import com.cristik.code.model.Model;
import com.cristik.code.table.BeanDefine;

import java.util.List;
import java.util.Map;

/**
 * @author cristik
 */
public interface Builder {

    /**
     * 根据context生成文件
     * @param models
     * @param context
     */
    void build(List<Model> models, Map<String,Object> context);

}
