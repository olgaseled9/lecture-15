package by.itacademy.javaenterprise.seledtsova.config;

import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("by.itacademy.javaenterprise")
@PropertySource("classpath:database.properties")
public class SpringConfig {

    @Value("${org.postgresql.Driver}")
    private String driverClassName;
    @Value("${jdbc:postgresql://localhost:5432/imagine_store}")
    private String databaseUrl;
    @Value("${postgres}")
    private String databaseUser;
    @Value("${postgres}")
    private String databasePassword;
    @Value("#{0}")
    private int databaseMixIdle;
    @Value("#{15}")
    private int databaseMaxIdle;

    @Bean
    public DataSource dataBean() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(databaseUrl);
        basicDataSource.setUsername(databaseUser);
        basicDataSource.setPassword(databasePassword);
        basicDataSource.setMinIdle(databaseMixIdle);
        basicDataSource.setMaxIdle(databaseMaxIdle);
        return basicDataSource;
    }

    @Bean
    OrderDaoImpl orderDaoBean() {
        return new OrderDaoImpl();
    }

    @Bean
    CustomerDaoImpl customerDaoBean() {
        return new CustomerDaoImpl();
    }
}

