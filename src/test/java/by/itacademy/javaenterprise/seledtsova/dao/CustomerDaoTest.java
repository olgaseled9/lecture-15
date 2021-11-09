package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CustomerDaoTest {

    private static CustomerDao customerDao;
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void setUpDB() {
        dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/imagine_store?useUnicode=true&characterEncoding=UTF-8",
                "postgres", "postgres");
        jdbcTemplate = new JdbcTemplate(dataSource);
        customerDao = new CustomerDaoImpl(jdbcTemplate);
    }

//    @Test
//    public void shouldFindCustomerByOrderIdWhichAreNotExist() {
//        int customerId = 3000;
//        Customer customer = customerDao.findCustomerByCustomerId(customerId);
//        assertEquals(0, customer.getCustomerId());
//    }
//
//    @Test
//    public void shouldCustomerTableIsEmpty() {
//        List<Customer> customers = customerDao.getAll();
//        assertFalse(customers.isEmpty(), "The customers table is empty");
//    }
//
//    @Test
//    public void updateCustomerByCustomerId() {
//        int id = 1;
//        Customer customer = new Customer();
//        int result = customerDao.updateCustomerByCustomerId(id, customer);
//        assertEquals(1, result);
//    }

}
