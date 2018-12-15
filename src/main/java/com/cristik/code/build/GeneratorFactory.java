package com.cristik.code.build;

import com.cristik.code.config.GeneratorConfig;
import com.cristik.code.convert.ConfigType;
import com.cristik.code.convert.Layer;
import com.cristik.code.convert.stragegy.NameStrategy;
import com.cristik.code.database.DataBase;
import com.cristik.code.database.DataBaseFactory;
import com.cristik.code.delegate.ConfigParserDelegate;
import com.cristik.code.exception.ParseException;
import com.cristik.code.model.Config;
import com.cristik.code.model.Model;
import com.cristik.code.table.BeanDefine;
import com.cristik.code.table.ConvertFormat;
import com.cristik.code.table.Table;
import com.cristik.code.table.TableInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author cristik
 */
public abstract class GeneratorFactory {

    private static Scheduler scheduler = new Scheduler();

    private static final Logger logger = LoggerFactory.getLogger(GeneratorFactory.class);

    private static final String GENERATOR_SETTING = "/generator.xml";

    private static String generatorSetting = GENERATOR_SETTING;

    public static void generate(List<Layer> layers) throws ParseException {
        Config config = ConfigParserDelegate.delegateParse(generatorSetting, ConfigType.xml);
        DataBase dataBase = DataBaseFactory.getDataBase(DataBaseFactory.DataBaseType.MYSQL);
        parseConfig(dataBase, config, layers);
    }

    public static void generate(List<Layer> layers, GeneratorConfig generatorConfig) throws ParseException {
        Config config = ConfigParserDelegate.delegateParse(generatorConfig, ConfigType.java);
        DataBase dataBase = DataBaseFactory.getDataBase(DataBaseFactory.DataBaseType.MYSQL);
        parseConfig(dataBase, config, layers);

    }

    protected static void parseConfig(DataBase dataBase, Config config, List<Layer> layers) {
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
     *
     * @param nameStrategy
     */
    public static void setNameStrategy(NameStrategy nameStrategy) {
        scheduler.setNameStrategy(nameStrategy);
    }
}
