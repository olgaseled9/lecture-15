package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.CustomerDao;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component("customerDaoBean")
public class CustomerDaoImpl implements CustomerDao {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public static final String SELECT_FROM_CUSTOMER_TABLE = "SELECT * FROM Customers ORDER BY last_name LIMIT 100 OFFSET 3";
    public static final String DELETE_CUSTOMER_FROM_CUSTOMER_TABLES = "DELETE FROM Customers WHERE customer_id = ?";
    public static final String SELECT_FROM_CUSTOMER_TABLE_CUSTOMER_ID = "SELECT customer_id, first_name, last_name FROM Customers WHERE customer_id=?";
    private static final String ADD_NEW_CUSTOMER = "INSERT INTO Customers (customer_id, first_name, last_name) VALUES (?,?,?)";
    private static final String UPDATE_CUSTOMER = "UPDATE Customers SET first_name=?, last_name=? WHERE customer_id=?";

    @Autowired
    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.update(ADD_NEW_CUSTOMER, customer.getCustomerId(), customer.getFirstName(),
                customer.getLastName());
    }

    @Override
    public List<Customer> getAll() {
        return jdbcTemplate.query(SELECT_FROM_CUSTOMER_TABLE, new CustomerMapper());
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        jdbcTemplate.update(DELETE_CUSTOMER_FROM_CUSTOMER_TABLES, customerId);
    }

    @Override
    public Customer findCustomerByCustomerId(Integer customerID) {
        return jdbcTemplate.query(SELECT_FROM_CUSTOMER_TABLE_CUSTOMER_ID, new Object[]{customerID},
                        new CustomerMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public int updateCustomerByCustomerId(int customerId, Customer customer) {
        return jdbcTemplate.update(UPDATE_CUSTOMER, customer.getFirstName(), customer.getLastName(), customerId);
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
