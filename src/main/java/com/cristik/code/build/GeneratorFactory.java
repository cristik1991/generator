package com.cristik.code.build;

import com.cristik.code.convert.ConfigType;
import com.cristik.code.convert.Layer;
import com.cristik.code.convert.stragegy.DefaultNameStrategy;
import com.cristik.code.convert.stragegy.NameStrategy;
import com.cristik.code.database.DataBase;
import com.cristik.code.database.DataBaseFactory;
import com.cristik.code.delegate.ConfigParserDelegate;
import com.cristik.code.exception.ParseException;
import com.cristik.code.model.Config;
import com.cristik.code.model.Model;
import com.cristik.code.table.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cristik
 */
public class GeneratorFactory {

    private static Scheduler scheduler = new Scheduler();

    private static final Logger logger = LoggerFactory.getLogger(GeneratorFactory.class);

    private static final String GENERATOR_SETTING = "/generator.xml";

    private static String generatorSetting = GENERATOR_SETTING;

    private static void generate(List<Layer> layers) throws ParseException {
        Config config = ConfigParserDelegate.delegateParse(generatorSetting, ConfigType.xml);
        DataBase dataBase = DataBaseFactory.getDataBase(DataBaseFactory.DataBaseType.MYSQL);
        for (Table table : config.getTables()) {
            TableInfo tableInfo = dataBase.queryTableInfo(config.getJdbcConnection(), table.getTableName());
            if (tableInfo == null) {
                logger.warn("Table={} not exist", table);
            } else {
                tableInfo.setTable(table);
                BeanDefine beanDefine = ConvertFormat.convert(tableInfo);
                List<Model> models = Lists.newArrayList();
                models.addAll(config.getJavaTemplates());
                models.addAll(config.getMapperTemplates());
                models.addAll(config.getHtmlTemplates());
                scheduler.build(models, beanDefine, layers);
            }
        }
    }

    /**
     * 指定配置文件位置
     *
     * @param generatorSetting
     */
    public static void setGeneratorSetting(String generatorSetting) {
        GeneratorFactory.generatorSetting = generatorSetting;
    }

    /**
     * 指定命名策略
     * @param nameStrategy
     */
    public static void setNameStrategy(NameStrategy nameStrategy) {
        scheduler.setNameStrategy(nameStrategy);
    }

    /**
     * 按照XML配置文件生成
     * @throws ParseException
     */
    public static void generate() throws ParseException {
        generate(null);
    }

    /**
     * 生成Controller
     */
    public static void genController() throws ParseException {
        generate(Arrays.asList(Layer.Controller));
    }

    /**
     * 生成Service
     */
    public static void genService() throws ParseException {
        generate(Arrays.asList(Layer.Service));
    }

    /**
     * 生成ServiceImpl
     */
    public static void genServiceImpl() throws ParseException {
        generate(Arrays.asList(Layer.ServiceImpl));
    }

    /**
     * 生成Dao
     */
    public static void genDao() throws ParseException {
        generate(Arrays.asList(Layer.Dao));
    }

    /**
     * 生成Entity
     */
    public static void genEntity() throws ParseException {
        generate(Arrays.asList(Layer.Entity));
    }

    /**
     * 生成Mapper文件
     */
    public static void genMapper() throws ParseException {
        generate(Arrays.asList(Layer.MapperXml));
    }
}
