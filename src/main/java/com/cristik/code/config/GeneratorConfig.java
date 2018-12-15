package com.cristik.code.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author cristik
 */

@Data
@Accessors(chain = true)
public class GeneratorConfig {

    private DataBase dataBase;

    private Controller controller;

    private Service service;

    private ServiceImpl serviceImpl;

    private Dao dao;

    private Entity entity;

    private Mapper mapper;

    private List<Table> tables;

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataBase {
        private String driverName;
        private String url;
        private String userName;
        private String password;
        private String schema;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Controller {
        private String directory;
        private String templatePath;
        private String packagePath;
        private String templateName;
        private boolean override = false;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Service {
        private String directory;
        private String templatePath;
        private String packagePath;
        private String templateName;
        private boolean override = false;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServiceImpl {
        private String directory;
        private String templatePath;
        private String packagePath;
        private String templateName;
        private boolean override = false;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dao {
        private String directory;
        private String templatePath;
        private String packagePath;
        private String templateName;
        private boolean override = false;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Entity {
        private String directory;
        private String templatePath;
        private String packagePath;
        private String templateName;
        private boolean override = false;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mapper {
        private String directory;
        private String templatePath;
        private String targetDirectory;
        private String templateName;
        private boolean override = false;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Table {
        private String prefix;
        private String tableName;
    }
}
