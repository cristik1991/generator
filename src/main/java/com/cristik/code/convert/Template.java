package com.cristik.code.convert;

/**
 * @author cristik
 */
public class Template {

    private static final String PACKAGE = "Package";
    private static final String IMPORT_LIST = "ImportList";
    private static final String CLASS_NAME = "ClassName";
    private static final String OBJECT_NAME = "ObjectName";
    /**
     * Table
     */
    public static final String TABLE_NAME = "tableName";
    /**
     * Entity
     */
    private static final String ENTITY = "entity";
    public static final String ENTITY_PACKAGE = ENTITY + PACKAGE;
    public static final String ENTITY_IMPORT_LIST = ENTITY + IMPORT_LIST;
    public static final String ENTITY_CLASS_NAME = ENTITY + CLASS_NAME;
    public static final String ENTITY_OBJECT_NAME = ENTITY + OBJECT_NAME;

    public static final String ENTITY_FIELD_LIST = "fields";
    public static final String ENTITY_ID_FIELD = "primaryKey";

    /**
     * Mapper
     */
    private static final String MAPPER = "mapper";
    public static final String MAPPER_PACKAGE = MAPPER + PACKAGE;
    public static final String MAPPER_IMPORT_LIST = MAPPER + IMPORT_LIST;
    public static final String MAPPER_CLASS_NAME = MAPPER + CLASS_NAME;
    public static final String MAPPER_OBJECT_NAME = MAPPER + OBJECT_NAME;
    /**
     * ServiceImpl
     */
    private static final String SERVICE_IMPL = "serviceImpl";
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_IMPL + PACKAGE;
    public static final String SERVICE_IMPL_IMPORT_LIST = SERVICE_IMPL + IMPORT_LIST;
    public static final String SERVICE_IMPL_CLASS_NAME = SERVICE_IMPL + CLASS_NAME;
    public static final String SERVICE_IMPL_OBJECT_NAME = SERVICE_IMPL + OBJECT_NAME;
    /**
     * Service
     */
    private static final String SERVICE = "service";
    public static final String SERVICE_PACKAGE = SERVICE + PACKAGE;
    public static final String SERVICE_IMPORT_LIST = SERVICE + IMPORT_LIST;
    public static final String SERVICE_CLASS_NAME = SERVICE + CLASS_NAME;
    public static final String SERVICE_OBJECT_NAME = SERVICE + OBJECT_NAME;
    /**
     * Controller
     */
    private static final String CONTROLLER = "controller";
    public static final String CONTROLLER_PACKAGE = CONTROLLER + PACKAGE;
    public static final String CONTROLLER_IMPORT_LIST = CONTROLLER + IMPORT_LIST;
    public static final String CONTROLLER_CLASS_NAME = CONTROLLER + CLASS_NAME;
    public static final String CONTROLLER_OBJECT_NAME = CONTROLLER + OBJECT_NAME;
    public static final String CONTROLLER_PATH = CONTROLLER + "Path";

    /**
     * MapperXml
     */
    public static final String MAPPER_XML_NAMESPACE = "namespace";
    public static final String MAPPER_XML_TYPE = "type";
    public static final String MAPPER_XML_COLUMNS = "columns";


}
