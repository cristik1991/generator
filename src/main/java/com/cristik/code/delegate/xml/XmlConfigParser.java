package com.cristik.code.delegate.xml;

import com.cristik.code.convert.Layer;
import com.cristik.code.delegate.ConfigParser;
import com.cristik.code.exception.ParseException;
import com.cristik.code.model.*;
import com.cristik.code.table.Table;
import com.cristik.code.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author cristik
 */
public class XmlConfigParser implements ConfigParser {

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 节点
     */
    private static final String CONFIGURATION = "configuration";
    private static final String PROPERTIES = "properties";
    private static final String CONTEXT = "context";
    private static final String CONNECTION = "connection";
    private static final String JAVA_MODELS = "javaModels";
    private static final String JAVA_MODEL = "javaModel";
    private static final String MAPPER_MODELS = "mapperModels";
    private static final String MAPPER_MODEL = "mapperModel";
    private static final String HTML_MODELS = "htmlModels";
    private static final String HTML_MODEL = "htmlModel";
    private static final String TABLES = "tables";
    private static final String TABLE = "table";
    private static final String PROPERTY = "property";

    /**
     * 属性
     */

    private static final String RESOURCE = "resource";
    private static final String NAME = "name";
    private static final String VALUE = "value";
    private static final String DIRECTORY = "directory";
    private static final String TEMPLATE_PATH = "templatePath";
    private static final String OVERRIDE = "override";
    private static final String PREFIX = "prefix";

    /**
     * property name
     */
    private static final String DRIVER_NAME = "driverName";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String SCHEMA = "schema";
    private static final String PACKAGE = "package";
    private static final String TEMPLATE_NAME = "templateName";
    private static final String TARGET_DIRECTORY = "targetDirectory";
    private static final String LAYER = "layer";
    private static final String TYPE = "type";

    /**
     * property value
     */

    private static final String CONTROLLER = "controller";
    private static final String SERVICE = "service";
    private static final String SERVICEIMPL = "serviceimpl";
    private static final String DAO = "dao";
    private static final String ENTITY = "entity";
    private static final String CREATE = "create";
    private static final String LIST = "list";
    private static final String UPDATE = "update";
    private static final String VIEW = "view";

    /**
     * other
     */
    private static final String OPEN = "${";
    private static final String CLOSE = "}";


    private String xmlConfigLocation;

    private Properties properties;

    public XmlConfigParser(String xmlConfigLocation) {
        this.properties = System.getProperties();
        this.xmlConfigLocation = xmlConfigLocation;
    }

    @Override
    public Config parse() throws ParseException {
        logger.debug("use {} to parse xml setting", this.getClass().toString());
        return processXml(validateXml());
    }

    private Element validateXml() throws ParseException {
        InputStream inputStream = XmlConfigParser.class.getResourceAsStream(xmlConfigLocation);
        if (inputStream == null) {
            throw new ParseException("xml config location is invalid");
        }
        InputSource inputSource = new InputSource(inputStream);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        Document document;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(inputSource);
        } catch (ParserConfigurationException e) {
            throw new ParseException(e.getMessage());
        } catch (SAXException e) {
            throw new ParseException(e.getMessage());
        } catch (IOException e) {
            throw new ParseException(e.getMessage());
        }
        Element rootNode = document.getDocumentElement();
        return rootNode;
    }

    /**
     * 处理XML配置
     *
     * @param rootNode
     * @return
     * @throws ParseException
     */
    private Config processXml(Element rootNode) throws ParseException {
        Config config = new Config();
        NodeList nodeList = rootNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (PROPERTIES.equals(childNode.getNodeName())) {
                parseProperties(config, childNode);
            } else if (CONTEXT.equals(childNode.getNodeName())) {
                parseContext(config, childNode);
            }
        }
        return config;
    }

    /**
     * 处理Properties配置
     * @param config
     * @param node
     * @throws ParseException
     */
    private void parseProperties(Config config, Node node) throws ParseException {
        Properties attributes = parseAttributes(node);
        String resource = attributes.getProperty(RESOURCE);
        if (!StringUtil.isNotBlank(resource)) {
            throw new ParseException("jdbc properties is not configured");
        }
        InputStream inputStream = XmlConfigParser.class.getResourceAsStream(resource);
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new ParseException(e.getMessage());
        }
    }

    private void parseContext(Config config, Node node) throws ParseException {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (CONNECTION.equals(childNode.getNodeName())) {
                parseConnection(config, childNode);
            } else if (JAVA_MODELS.equals(childNode.getNodeName())) {
                parseJavaModels(config, childNode);
            } else if (MAPPER_MODELS.equals(childNode.getNodeName())) {
                parseXmlModels(config, childNode);
            } else if (HTML_MODELS.equals(childNode.getNodeName())) {
                parseHtmlModels(config, childNode);
            } else if (TABLES.equals(childNode.getNodeName())) {
                parseTables(config, childNode);
            }
        }

    }

    private void parseConnection(Config config, Node node) {
        NodeList nodeList = node.getChildNodes();
        Properties properties = new Properties();
        processNodeList(properties, nodeList);
        JdbcConnection jdbcConnection = config.getJdbcConnection();
        jdbcConnection.setDriverName(properties.getProperty(DRIVER_NAME));
        jdbcConnection.setSchema(properties.getProperty(SCHEMA));
        jdbcConnection.setUrl(properties.getProperty(URL));
        jdbcConnection.setUserName(properties.getProperty(USERNAME));
        jdbcConnection.setPassword(properties.getProperty(PASSWORD));
    }

    private void parseJavaModels(Config config, Node node) throws ParseException {
        Properties attributes = parseAttributes(node);
        String directory = attributes.getProperty(DIRECTORY);
        String templatePath = attributes.getProperty(TEMPLATE_PATH);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (JAVA_MODEL.equals(childNode.getNodeName())) {
                parseJavaModel(config, childNode);
            }
        }
        List<JavaModel> javaModels = config.getJavaTemplates();
        for (JavaModel javaModel : javaModels) {
            javaModel.setDirectory(directory);
            javaModel.setTemplatePath(templatePath);
        }
    }

    private void parseXmlModels(Config config, Node node) throws ParseException {
        Properties attributes = parseAttributes(node);
        String directory = attributes.getProperty(DIRECTORY);
        String templatePath = attributes.getProperty(TEMPLATE_PATH);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (MAPPER_MODEL.equals(childNode.getNodeName())) {
                parseXmlModel(config, childNode);
            }
        }
        List<MapperModel> mapperModels = config.getMapperTemplates();
        for (MapperModel mapperModel : mapperModels) {
            mapperModel.setDirectory(directory);
            mapperModel.setTemplatePath(templatePath);
        }
    }

    private void parseHtmlModels(Config config, Node node) throws ParseException {
        Properties attributes = parseAttributes(node);
        String directory = attributes.getProperty(DIRECTORY);
        String templatePath = attributes.getProperty(TEMPLATE_PATH);
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (HTML_MODEL.equals(childNode.getNodeName())) {
                parseHtmlModel(config, childNode);
            }
        }
        List<HtmlModel> htmlModels = config.getHtmlTemplates();
        for (HtmlModel model : htmlModels) {
            model.setDirectory(directory);
            model.setTemplatePath(templatePath);
        }
    }

    private void parseTables(Config config, Node node) {

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (TABLE.equals(childNode.getNodeName())) {
                parseTable(config, childNode);
            }
        }

    }

    private void parseTable(Config config, Node node) {
        List<Table> tables = config.getTables();
        Properties attributes = parseAttributes(node);
        String prefix = attributes.getProperty(PREFIX);
        Table table = new Table();
        if (prefix != null) {
            table.setPrefix(prefix);
        } else {
            table.setPrefix("");
        }
        table.setTableName(node.getFirstChild().getNodeValue());
        table.setDisplayTableName(table.getTableName().substring(table.getPrefix().length()));
        tables.add(table);
    }

    private void parseJavaModel(Config config, Node node) throws ParseException {
        JavaModel javaModel = new JavaModel();
        Properties attributes = parseAttributes(node);
        javaModel.setOverride(getOverride(attributes.getProperty(OVERRIDE)));
        Properties properties = new Properties();
        NodeList nodeList = node.getChildNodes();
        processNodeList(properties, nodeList);
        javaModel.setTemplateName(properties.getProperty(TEMPLATE_NAME));
        javaModel.setTargetDirectory(properties.getProperty(PACKAGE));
        javaModel.setLayer(getLayer(properties.getProperty(LAYER)));
        config.getJavaTemplates().add(javaModel);
    }

    /**
     * 处理子节点
     *
     * @param properties
     * @param nodeList
     */
    private void processNodeList(Properties properties, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (PROPERTY.equals(childNode.getNodeName())) {
                parseProperty(properties, childNode);
            }
        }
    }

    private void parseXmlModel(Config config, Node node) throws ParseException {
        MapperModel mapperModel = new MapperModel();
        Properties attributes = parseAttributes(node);
        mapperModel.setOverride(getOverride(attributes.getProperty(OVERRIDE)));
        Properties properties = new Properties();
        NodeList nodeList = node.getChildNodes();
        processNodeList(properties, nodeList);
        mapperModel.setTargetDirectory(properties.getProperty(TARGET_DIRECTORY));
        mapperModel.setLayer(Layer.MapperXml);
        mapperModel.setTemplateName(properties.getProperty(TEMPLATE_NAME));
        config.getMapperTemplates().add(mapperModel);

    }

    private void parseHtmlModel(Config config, Node node) throws ParseException {
        HtmlModel htmlModel = new HtmlModel();
        Properties attributes = parseAttributes(node);
        htmlModel.setOverride(getOverride(attributes.getProperty(OVERRIDE)));
        Properties properties = new Properties();
        NodeList nodeList = node.getChildNodes();
        processNodeList(properties, nodeList);
        htmlModel.setTemplateName(properties.getProperty(TEMPLATE_NAME));
        htmlModel.setTargetDirectory(properties.getProperty(TARGET_DIRECTORY));
        htmlModel.setLayer(getLayer(properties.getProperty(TYPE)));
        config.getHtmlTemplates().add(htmlModel);
    }

    private void parseProperty(Properties properties, Node node) {
        Properties attributes = parseAttributes(node);
        String name = attributes.getProperty(NAME);
        String value = attributes.getProperty(VALUE);
        properties.put(name, value);
    }

    private Properties parseAttributes(Node node) {
        Properties attributes = new Properties();
        NamedNodeMap nnm = node.getAttributes();
        for (int i = 0; i < nnm.getLength(); i++) {
            Node attribute = nnm.item(i);
            String value = parsePropertyTokens(attribute.getNodeValue());
            attributes.put(attribute.getNodeName(), value);
        }
        return attributes;
    }

    private String parsePropertyTokens(String string) {


        String newString = string;
        if (newString != null) {
            int start = newString.indexOf(OPEN);
            int end = newString.indexOf(CLOSE);
            while (start > -1 && end > start) {
                String prepend = newString.substring(0, start);
                String append = newString.substring(end + CLOSE.length());
                String propName = newString.substring(start + OPEN.length(), end);
                String propValue = properties.getProperty(propName);
                if (propValue != null) {
                    newString = prepend + propValue + append;
                }
                start = newString.indexOf(OPEN, end);
                end = newString.indexOf(CLOSE, end);
            }
        }
        return newString;
    }

    private Layer getLayer(String layer) throws ParseException {
        if (layer == null || layer.length() == 0) {
            throw new ParseException("layer is not configured");
        }
        layer = layer.toLowerCase();
        switch (layer) {
            case CONTROLLER:
                return Layer.Controller;
            case SERVICE:
                return Layer.Service;
            case SERVICEIMPL:
                return Layer.ServiceImpl;
            case DAO:
                return Layer.Dao;
            case ENTITY:
                return Layer.Entity;
            case CREATE:
                return Layer.create;
            case LIST:
                return Layer.list;
            case UPDATE:
                return Layer.update;
            case VIEW:
                return Layer.view;
            default:
                throw new ParseException("no match layer error");
        }
    }

    private boolean getOverride(String override) throws ParseException {
        if (override == null || override.length() == 0) {
            return false;
        }
        override = override.toLowerCase();
        switch (override) {
            case "false":
                return false;
            case "true":
                return true;
            default:
                throw new ParseException("override is not match");
        }
    }

}
