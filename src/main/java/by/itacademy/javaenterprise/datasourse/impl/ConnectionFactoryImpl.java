package by.itacademy.javaenterprise.datasourse.impl;

import by.itacademy.javaenterprise.datasourse.ConnectionFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private final static Logger logger = LoggerFactory.getLogger(ConnectionFactoryImpl.class);
    private static BasicDataSource basicDataSource;

    @Override
    public Connection getConnection() throws SQLException {
        if (basicDataSource == null) {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String url = resource.getString("DATABASE_URL");
            String user = resource.getString("DATABASE_USER");
            String password = resource.getString("DATABASE_PASSWORD");
            String driver = resource.getString("DATABASE_DRIVER");
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            dataSource.setDriverClassName(driver);
            dataSource.setMinIdle(15);
            dataSource.setMaxIdle(25);
            dataSource.setMaxOpenPreparedStatements(250);
            basicDataSource = dataSource;
        }
        try {
            return basicDataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalArgumentException("Connection is not available", e);
        }
    }

    public void setBasicDataSource(BasicDataSource basicDataSource) {
    }
}
