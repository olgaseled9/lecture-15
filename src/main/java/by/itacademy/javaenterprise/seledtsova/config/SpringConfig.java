package by.itacademy.javaenterprise.seledtsova.config;

import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan("by.itacademy.javaenterprise")
@PropertySource("classpath:database.properties")
public class SpringConfig {

    @Value("${database.driver}")
    private String driverClassName;
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.user}")
    private String databaseUser;
    @Value("${database.password}")
    private String databasePassword;
    @Value("${database.minIdle}")
    private int databaseMixIdle;
    @Value("${database.maxIdle}")
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
        return new OrderDaoImpl(namedParameterJdbcTemplate());
    }

    @Bean
    CustomerDaoImpl customerDaoBean() {
        return new CustomerDaoImpl(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataBean());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate());
    }
}

