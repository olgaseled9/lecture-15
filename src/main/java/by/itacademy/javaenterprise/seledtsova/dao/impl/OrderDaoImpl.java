package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component("orderDaoBean")
public class OrderDaoImpl implements OrderDao {
    private final static Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private MapSqlParameterSource mapSqlParameterSource;

    public static final String SELECT_FROM_ORDER_TABLE = "SELECT * FROM Orders ORDER BY order_id LIMIT 100 OFFSET 1;";
    public static final String DELETE_ORDER_FROM_CUSTOMER_TABLES = "DELETE FROM Orders WHERE order_id = :order_id";
    public static final String SELECT_FROM_ORDER_TABLE_CUSTOMER_ID = "SELECT * FROM Orders WHERE customer_id=?";
    private static final String ADD_NEW_ORDER = "INSERT INTO Orders (order_id, customer_id, date_order) VALUES (:order_id, :customer_id, :date_order)";
    private static final String UPDATE_ORDER = "UPDATE Orders SET customer_id=:customer_id, date_order=:date_order WHERE order_id=?";

    @Autowired
    public OrderDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void addOrder(Order order) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("order_id", order.getOrderId())
                .addValue("customer_id", order.getCustomerId())
                .addValue("date_order", order.getDateOrder());
        namedParameterJdbcTemplate.update(ADD_NEW_ORDER, namedParameter);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("order_id", orderId);
        try {
            namedParameterJdbcTemplate.update(DELETE_ORDER_FROM_CUSTOMER_TABLES, namedParameters);
            logger.info("A row was removed successful from the ORDERS table" + orderId);
        } catch (DataAccessException e) {
            logger.error("No order found with ID " + orderId);
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            return namedParameterJdbcTemplate.query(SELECT_FROM_ORDER_TABLE, new BeanPropertyRowMapper<>(Order.class));
        } catch (DataAccessException e) {
            logger.error("Get all order is not avaliable " + e);
            return new ArrayList<Order>();
        }
    }

    @Override
    public void update(int orderId, Order order) {
        mapSqlParameterSource.addValue("order_id", orderId);
        mapSqlParameterSource.addValue("customer_id", order.getCustomerId());
        mapSqlParameterSource.addValue("date_order", order.getDateOrder());
        try {
            namedParameterJdbcTemplate.update(UPDATE_ORDER, mapSqlParameterSource);
        } catch (DataAccessException e) {
            logger.error("Update order table by orderId is not impossible " + e);
        }
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
