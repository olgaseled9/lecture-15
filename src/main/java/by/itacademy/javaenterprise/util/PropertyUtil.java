package by.itacademy.javaenterprise.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {

    private final static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static PropertyUtil instance;
    private final Properties properties;

    public PropertyUtil() {
        this.properties = new Properties();
        try {
            InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream("database.properties");
            properties.load(propertiesStream);
        } catch (IOException exception) {
            logger.error("Config property file is not found", exception);
            throw new IllegalArgumentException("Config property file is not found", exception);
        }
    }

    public static PropertyUtil getInstance() {
        if (instance == null) {
            instance = new PropertyUtil();
        }
        return instance;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}


