package com.cristik.code.convert.stragegy;

import com.cristik.code.convert.Layer;

/**
 * @author cristik
 */
public interface NameStrategy {

    /**
     * 根据层级自定义名称
     * @param layer
     * @param objectName
     * @return
     */
    String formatName(Layer layer, String objectName);

}
