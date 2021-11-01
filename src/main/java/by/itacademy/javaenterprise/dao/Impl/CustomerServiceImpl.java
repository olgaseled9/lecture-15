package by.itacademy.javaenterprise.dao.Impl;

import by.itacademy.javaenterprise.dao.CustomerService;
import by.itacademy.javaenterprise.entity.Customer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

public class CustomerServiceImpl implements CustomerService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private static BasicDataSource basicDataSource;
    public static final String SELECT_FROM_CUSTOMER_TABLE = "SELECT * FROM Customers ORDER BY last_name LIMIT 1 OFFSET 3";
    public static final String DELETE_CUSTOMER_FROM_CUSTOMER_TABLES = "DELETE FROM customers WHERE customer_id = ?";
    public static final String SELECT_FROM_CUSTOMER_TABLE_CUSTOMER_ID = "SELECT customer_id FROM Customers WHERE customer_id=?";

    @Override
    public Customer addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add  " + customer.getClass().getName(), e);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = basicDataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_CUSTOMER_TABLE);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customers.add(customer);
            }
        } catch (SQLException exception) {
            logger.error("Not able to add  customer", exception);
            throw new RuntimeException("Connection is not available", exception);
        } finally {
            close(statement);
        }
        return customers;
    }


    @Override
    public void deleteCustomerById(Integer customerId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = basicDataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_FROM_CUSTOMER_TABLES);
            preparedStatement.setInt(1, customerId);
            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Deleting customer from database failed", e);
        }
    }

    @Override
    public Customer findCustomerByCustomerId(Integer customerID) {
        Connection connection = null;
        Customer customer = new Customer();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_FROM_CUSTOMER_TABLE_CUSTOMER_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            customer.setCustomerId(resultSet.getInt(5));
        } catch (SQLException exception) {
            logger.error("Not able to find customer by customerId" + exception);
        } finally {
            close(preparedStatement);
        }
        return customer;
    }

    @Override
    public Object create(Object o) throws SQLException {
        return null;
    }

    @Override
    public void update(Object o) throws SQLException {

    }

    @Override
    public int delete(Object id) throws SQLException {
        return 0;
    }

    @Override
    public Object get(Object id) throws SQLException {
        return null;
    }
}
