package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

@Component("orderDaoBean")
public class OrderDaoImpl implements OrderDao {
    private final static Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    public static final String SELECT_FROM_ORDER_TABLE = "SELECT * FROM Orders ORDER BY order_id LIMIT 100 OFFSET 1;";
    public static final String DELETE_ORDER_FROM_CUSTOMER_TABLES = "DELETE FROM Orders WHERE order_id = ?";
    public static final String SELECT_FROM_ORDER_TABLE_CUSTOMER_ID = "SELECT customer_id FROM Orders WHERE customer_id=?";
    private static final String ADD_NEW_ORDER = "INSERT INTO Orders (order_id, customer_id, date_order) VALUES (?,?,?)";

    @Override
    public Order addOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_ORDER);
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setString(3, order.getDateOrder());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add  " + order.getClass().getName(), e);
        } finally {
            close(preparedStatement);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ORDER_TABLE);
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setDateOrder(resultSet.getString("date_order"));
                orders.add(order);
            }
        } catch (SQLException exception) {
            logger.error("Not able to add  order", exception);
            throw new RuntimeException("Connection is not available", exception);
        } finally {
            close(statement);
        }
        return orders;
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ORDER_FROM_CUSTOMER_TABLES);
            preparedStatement.setInt(1, orderId);
            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Deleting order from database failed", e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Order findOrderByCustomerId(Integer customerID) {
        Connection connection = null;
        Order order = new Order();
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_ORDER_TABLE_CUSTOMER_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order.setCustomerId(resultSet.getInt(5));
        } catch (SQLException exception) {
            logger.error("Not able to find customer by customerId" + exception);
        } finally {
            close(preparedStatement);
        }
        return order;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


