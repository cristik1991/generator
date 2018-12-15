package com.cristik.code.delegate.java;

import com.cristik.code.config.GeneratorConfig;
import com.cristik.code.convert.Layer;
import com.cristik.code.delegate.ConfigParser;
import com.cristik.code.exception.ParseException;
import com.cristik.code.model.Config;
import com.cristik.code.model.JavaModel;
import com.cristik.code.model.JdbcConnection;
import com.cristik.code.model.MapperModel;
import com.cristik.code.table.Table;
import com.cristik.code.util.StringUtil;

import java.util.List;

/**
 * @author cristik
 */
public class JavaConfigParser implements ConfigParser {

    private GeneratorConfig generatorConfig;


    public JavaConfigParser(GeneratorConfig generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    @Override
    public Config parse() throws ParseException {
        Config config = new Config();
        parseConnection(config);
        parseJavaModels(config);
        parseXmlModels(config);
        parseTables(config);
        return config;
    }

    private void parseConnection(Config config) {
        JdbcConnection jdbcConnection = config.getJdbcConnection();
        GeneratorConfig.DataBase dataBase = generatorConfig.getDataBase();
        jdbcConnection.setDriverName(dataBase.getDriverName())
                .setSchema(dataBase.getSchema())
                .setUrl(dataBase.getUrl())
                .setUserName(dataBase.getUserName())
                .setPassword(dataBase.getPassword());
    }

    private void parseJavaModels(Config config) {
        if (generatorConfig.getController() != null) {
            GeneratorConfig.Controller controller = generatorConfig.getController();
            JavaModel controllerModel = parseJavaModel(controller.getTemplateName(), controller.getDirectory()
                    , Layer.Controller, controller.isOverride(), controller.getPackagePath()
                    , controller.getTemplatePath());
            config.getJavaTemplates().add(controllerModel);
        }
        if (generatorConfig.getService() != null) {
            GeneratorConfig.Service service = generatorConfig.getService();
            JavaModel serviceModel = parseJavaModel(service.getTemplateName(), service.getDirectory(), Layer.Service
                    , service.isOverride(), service.getPackagePath(), service.getTemplatePath());
            config.getJavaTemplates().add(serviceModel);
        }
        if (generatorConfig.getServiceImpl() != null) {
            GeneratorConfig.ServiceImpl serviceImpl = generatorConfig.getServiceImpl();
            JavaModel serviceImplModel = parseJavaModel(serviceImpl.getTemplateName(), serviceImpl.getDirectory(), Layer.ServiceImpl
                    , serviceImpl.isOverride(), serviceImpl.getPackagePath(), serviceImpl.getTemplatePath());
            config.getJavaTemplates().add(serviceImplModel);
        }
        if (generatorConfig.getDao() != null) {
            GeneratorConfig.Dao dao = generatorConfig.getDao();
            JavaModel daoModel = parseJavaModel(dao.getTemplateName(), dao.getDirectory(), Layer.Dao, dao.isOverride()
                    , dao.getPackagePath(), dao.getTemplatePath());
            config.getJavaTemplates().add(daoModel);
        }
        if (generatorConfig.getEntity() != null) {
            GeneratorConfig.Entity entity = generatorConfig.getEntity();
            JavaModel daoModel = parseJavaModel(entity.getTemplateName(), entity.getDirectory(), Layer.Entity, entity.isOverride()
                    , entity.getPackagePath(), entity.getTemplatePath());
            config.getJavaTemplates().add(daoModel);
        }
    }

    private JavaModel parseJavaModel(String templateName, String directory, Layer layer, boolean override
            , String packagePath, String templatePath) {
        JavaModel javaModel = new JavaModel();
        javaModel.setTemplateName(templateName)
                .setDirectory(directory)
                .setLayer(layer)
                .setOverride(override)
                .setTargetDirectory(packagePath)
                .setTemplatePath(templatePath);
        return javaModel;
    }

    private void parseXmlModels(Config config) {
        GeneratorConfig.Mapper mapper = generatorConfig.getMapper();
        MapperModel mapperModel = new MapperModel();
        mapperModel.setTemplatePath(mapper.getTemplatePath());
        mapperModel.setTemplateName(mapper.getTemplateName());
        mapperModel.setDirectory(mapper.getDirectory());
        mapperModel.setOverride(mapper.isOverride());
        mapperModel.setTargetDirectory(mapper.getTargetDirectory());
        mapperModel.setLayer(Layer.MapperXml);
        config.getMapperTemplates().add(mapperModel);
    }

    private void parseTables(Config config) {
        List<Table> tables = config.getTables();
        List<GeneratorConfig.Table> tableList = generatorConfig.getTables();
        if (tableList != null && tableList.size() > 0) {
            tableList.stream().forEach(table -> {
                Table tableConfig = new Table();
                if (StringUtil.isNotBlank(table.getPrefix())) {
                    tableConfig.setPrefix(table.getPrefix());
                }else{
                    tableConfig.setPrefix("");
                }
                tableConfig.setTableName(table.getTableName());
                tableConfig.setDisplayTableName(table.getTableName().substring(table.getPrefix().length()));
                tables.add(tableConfig);
            });
        }
    }
}
