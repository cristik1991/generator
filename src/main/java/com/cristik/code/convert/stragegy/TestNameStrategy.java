package com.cristik.code.convert.stragegy;

import com.cristik.code.convert.Layer;

/**
 * @author cristik
 */
public class TestNameStrategy implements NameStrategy {


    @Override
    public String formatName(Layer layer, String className) {
        switch (layer) {
            case Entity:
                return className;
            case Dao:
                return className + "Dao";
            case ServiceImpl:
                return className + "ServiceImpl";
            case Service:
                return "I" + className + "Service";
            case Controller:
                return className + "Controller";
            case MapperXml:
                return className + "Mapper";
            default:
                throw new RuntimeException("No supported layer");
        }

    }
}
