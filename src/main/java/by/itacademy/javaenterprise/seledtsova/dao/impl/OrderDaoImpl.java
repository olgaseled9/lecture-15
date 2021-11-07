package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

@Component("orderDaoBean")
public class OrderDaoImpl implements OrderDao {
    private final static Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public static final String SELECT_FROM_ORDER_TABLE = "SELECT * FROM Orders ORDER BY order_id LIMIT 100 OFFSET 1;";
    public static final String DELETE_ORDER_FROM_CUSTOMER_TABLES = "DELETE FROM Orders WHERE order_id = ?";
    public static final String SELECT_FROM_ORDER_TABLE_CUSTOMER_ID = "SELECT * FROM Orders WHERE customer_id=?";
    private static final String ADD_NEW_ORDER = "INSERT INTO Orders (order_id, customer_id, date_order) VALUES (?,?,?)";
    private static final String UPDATE_ORDER = "UPDATE Orders SET customer_id=?, data_order=? WHERE order_id=?";

@Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addOrder(Order order) {
        jdbcTemplate.update(ADD_NEW_ORDER, order.getOrderId(), order.getCustomerId(), order.getDateOrder());
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(SELECT_FROM_ORDER_TABLE, new OrderMapper());
    }

    @Override
    public void deleteOrderById(Integer orderId) {
    jdbcTemplate.update(DELETE_ORDER_FROM_CUSTOMER_TABLES,orderId);
    }

    @Override
    public Order findOrderByCustomerId(Integer customerID) {
        return jdbcTemplate.query(SELECT_FROM_ORDER_TABLE_CUSTOMER_ID, new Object[]{customerID}, new OrderMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public void update(int orderId, Order order) {
        jdbcTemplate.update(UPDATE_ORDER, order.getCustomerId(), order.getDateOrder(), orderId);
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}


