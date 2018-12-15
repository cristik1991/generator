package com.cristik.code.delegate;

import com.cristik.code.config.GeneratorConfig;
import com.cristik.code.delegate.java.JavaConfigParser;
import com.cristik.code.exception.ParseException;
import com.cristik.code.convert.ConfigType;
import com.cristik.code.delegate.xml.XmlConfigParser;
import com.cristik.code.model.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cristik
 */
public class ConfigParserDelegate {

    private static final Logger logger = LoggerFactory.getLogger(ConfigParserDelegate.class);

    private static ConfigParser xmlDelegate(String resource) {
        return new XmlConfigParser(resource);
    }

    private static ConfigParser javaDelegate(GeneratorConfig generatorConfig) {
        return new JavaConfigParser(generatorConfig);
    }

    public static Config delegateParse(Object setting, ConfigType configType) throws ParseException {
        ConfigParser configParser;
        switch (configType) {
            case xml:
                logger.debug("use xml parse to deal with resource");
                configParser = xmlDelegate((String) setting);
                break;
            case java:
                logger.debug("use java code setting to deal with resource");
                if (setting instanceof GeneratorConfig) {
                    configParser = javaDelegate((GeneratorConfig) setting);
                }else {
                    throw new ParseException("Expected Config with GeneratorConfig");
                }
                break;
            default:
                logger.error("config type is not specified");
                throw new ParseException("no match configType");
        }
        return configParser.parse();
    }
}
