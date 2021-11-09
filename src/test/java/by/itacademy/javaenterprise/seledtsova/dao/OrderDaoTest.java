package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDaoTest {
    private static OrderDao orderDao;
    private static DataSource dataSource;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @BeforeAll
    void setUpDB() {
        dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/imagine_store?useUnicode=true&characterEncoding=UTF-8",
                "postgres", "postgres");
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        orderDao = new OrderDaoImpl(namedParameterJdbcTemplate);
    }
}
