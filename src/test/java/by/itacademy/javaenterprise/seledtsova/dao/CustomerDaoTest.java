package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerDaoTest {

    private static CustomerDao customerDao;
    private static OrderDao orderDao;
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @BeforeClass
    public static void setUpDB() {
        dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/imagine_store?useUnicode=true&characterEncoding=UTF-8",
                "postgres", "postgres");
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
        customerDao = new CustomerDaoImpl(jdbcTemplate);
        orderDao=new OrderDaoImpl(namedParameterJdbcTemplate);

    }

    @Test
    public void testFindCustomerById() {
        int customerId = 9;
        Customer customer = customerDao.findCustomerByCustomerId(customerId);
        assertEquals(customerId, customer.getCustomerId());
    }

     @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer(1, "Anna", "Korenina");
        customer.setCustomerId(1);
        customer.setFirstName("Anna");
        customer.setLastName("Korenina");
        customerDao.updateCustomerByCustomerId(1, customer);
        customer.setCustomerId(1);
        assertEquals(customer, customerDao.findCustomerByCustomerId(1));
    }

}


