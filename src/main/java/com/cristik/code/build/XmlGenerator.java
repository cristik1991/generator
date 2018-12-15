package com.cristik.code.build;

import com.cristik.code.convert.Layer;
import com.cristik.code.exception.ParseException;

import java.util.Arrays;

/**
 * @author cristik
 */
public class XmlGenerator {

    /**
     * 按照XML配置文件生成
     *
     * @throws ParseException
     */
    public static void generate() throws ParseException {
        GeneratorFactory.generate(null);
    }

    /**
     * 生成Controller
     */
    public static void genController() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Controller));
    }

    /**
     * 生成Service
     */
    public static void genService() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Service));
    }

    /**
     * 生成ServiceImpl
     */
    public static void genServiceImpl() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.ServiceImpl));
    }

    /**
     * 生成Dao
     */
    public static void genDao() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Dao));
    }

    /**
     * 生成Entity
     */
    public static void genEntity() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Entity));
    }

    /**
     * 生成Mapper文件
     */
    public static void genMapper() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.MapperXml));
    }

}
