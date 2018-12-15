package com.cristik.code.build;

import com.cristik.code.config.GeneratorConfig;
import com.cristik.code.convert.Layer;
import com.cristik.code.exception.ParseException;

import java.util.Arrays;

/**
 * @author cristik
 */
public class JavaGenerator {

    private static GeneratorConfig generatorConfig;

    /**
     * 按照XML配置文件生成
     *
     * @throws ParseException
     */
    public static void generate() throws ParseException {
        GeneratorFactory.generate(null, generatorConfig);
    }

    /**
     * 生成Controller
     */
    public static void genController() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Controller), generatorConfig);
    }

    /**
     * 生成Service
     */
    public static void genService() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Service), generatorConfig);
    }

    /**
     * 生成ServiceImpl
     */
    public static void genServiceImpl() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.ServiceImpl), generatorConfig);
    }

    /**
     * 生成Dao
     */
    public static void genDao() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Dao), generatorConfig);
    }

    /**
     * 生成Entity
     */
    public static void genEntity() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.Entity), generatorConfig);
    }

    /**
     * 生成Mapper文件
     */
    public static void genMapper() throws ParseException {
        GeneratorFactory.generate(Arrays.asList(Layer.MapperXml), generatorConfig);
    }

    public static void setGeneratorConfig(GeneratorConfig generatorConfig) {
        JavaGenerator.generatorConfig = generatorConfig;
    }
}
