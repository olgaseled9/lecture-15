package by.itacademy.javaenterprise.dao.impl;

import by.itacademy.javaenterprise.dao.OrderService;
import by.itacademy.javaenterprise.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

@Component ("orderBean")
public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private DataSource dataSource;

    public static final String SELECT_FROM_ORDER_TABLE = "SELECT * FROM Orders ORDER BY order_id LIMIT 1 OFFSET 1;";
    public static final String DELETE_ORDER_FROM_CUSTOMER_TABLES = "DELETE FROM Orders WHERE order_id = ?";


    @Override
    public Order addOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setString(3, order.getDateOrder());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add  " + order.getClass().getName(), e);
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
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


