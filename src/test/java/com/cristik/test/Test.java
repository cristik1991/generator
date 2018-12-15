package com.cristik.test;

import com.cristik.code.build.JavaGenerator;
import com.cristik.code.build.XmlGenerator;
import com.cristik.code.config.GeneratorConfig;
import com.cristik.code.exception.ParseException;

import java.util.Arrays;

/**
 * @author cristik
 */
public class Test {

//    @Test
    public void generateWithXml() throws ParseException {
        XmlGenerator.generate();
    }

//    @Test
    public void generateWithJavaConfig() throws ParseException {
        GeneratorConfig generatorConfig = buildJavaConfig();
        JavaGenerator.setGeneratorConfig(generatorConfig);
        JavaGenerator.generate();
    }

    private GeneratorConfig buildJavaConfig() {
        GeneratorConfig generatorConfig = new GeneratorConfig();
        String javaDirectory = "../modules/shinetest/src/main/java";
        String mapperDirectory = "../modules/shinetest/src/main/resources";
        String javaTemplatePath = "src/test/resources/templates/codeTemplate/crud";
        String mapperTemplatePath = "src/test/resources/templates/codeTemplate/mybatis";

        GeneratorConfig.Controller controller = new GeneratorConfig.Controller()
                .setDirectory(javaDirectory)
                .setOverride(false)
                .setPackagePath("com.stellar.controller")
                .setTemplatePath(javaTemplatePath)
                .setTemplateName("Controller.ftl");

        GeneratorConfig.Service service = new GeneratorConfig.Service()
                .setDirectory(javaDirectory)
                .setOverride(false)
                .setPackagePath("com.stellar.service")
                .setTemplatePath(javaTemplatePath)
                .setTemplateName("Service.ftl");

        GeneratorConfig.ServiceImpl serviceImpl = new GeneratorConfig.ServiceImpl()
                .setDirectory(javaDirectory)
                .setOverride(false)
                .setPackagePath("com.stellar.service.impl")
                .setTemplatePath(javaTemplatePath)
                .setTemplateName("ServiceImpl.ftl");

        GeneratorConfig.Dao dao = new GeneratorConfig.Dao()
                .setDirectory(javaDirectory)
                .setOverride(false)
                .setPackagePath("com.stellar.dao")
                .setTemplatePath(javaTemplatePath)
                .setTemplateName("Dao.ftl");

        GeneratorConfig.Entity entity = new GeneratorConfig.Entity()
                .setDirectory(javaDirectory)
                .setOverride(false)
                .setPackagePath("com.stellar.entity.po")
                .setTemplatePath(javaTemplatePath)
                .setTemplateName("Entity.ftl");

        GeneratorConfig.Mapper mapper = new GeneratorConfig.Mapper()
                .setDirectory(mapperDirectory)
                .setTemplatePath(mapperTemplatePath)
                .setOverride(false)
                .setTargetDirectory("/mapping/shinetest")
                .setTemplateName("Mapper.ftl");


        generatorConfig.setController(controller);
        generatorConfig.setService(service);
        generatorConfig.setServiceImpl(serviceImpl);
        generatorConfig.setDao(dao);
        generatorConfig.setEntity(entity);
        generatorConfig.setMapper(mapper);

        GeneratorConfig.DataBase dataBase = new GeneratorConfig.DataBase()
                .setUrl("jdbc:mysql://localhost:3306")
                .setDriverName("com.mysql.jdbc.Driver")
                .setSchema("test")
                .setUserName("root")
                .setPassword("123456");

        generatorConfig.setDataBase(dataBase);


        generatorConfig.setTables(Arrays.asList(
                new GeneratorConfig.Table("sys_", "sys_config")
        ));

        return generatorConfig;
    }

}
