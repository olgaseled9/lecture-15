package by.itacademy.javaenterprise.seledtsova.dao.impl;

import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

@Component("itemDaoBean")
public class ItemDaoImpl implements ItemDao {

    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);
    private static final String SELECT_FROM_ITEM_TABLE = "SELECT id, name, price, description FROM item ORDER BY id";
    private static final String DELETE_ITEM_FROM_ITEM_TABLE = "DELETE FROM item WHERE id = ?";
    private static final String ADD_NEW_ITEM = "INSERT INTO item (id, name, price, description) VALUES (?,?,?,?)";
    private static final String SELECT_BY_ID_FROM_ITEM_TABLE = "SELECT id,name,price,description FROM item WHERE id =?";

    private BasicDataSource basicDataSource;

    public ItemDaoImpl() {
    }

    @Autowired
    public ItemDaoImpl(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    @Override
    public Item saveItem(Item item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = basicDataSource.getConnection();
            preparedStatement = connection.prepareStatement(ADD_NEW_ITEM);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setBigDecimal(3, item.getPrice());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Not able to add item " + e.getMessage(), e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = basicDataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ITEM_TABLE);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setDescription(resultSet.getString("description"));
                items.add(item);
            }
        } catch (SQLException exception) {
            logger.error("Not able to add  customer" + exception.getMessage(), exception);
            throw new RuntimeException("Connection is not available" + exception.getMessage(), exception);
        } finally {
            close(statement);
            close(connection);
        }
        return items;
    }

    @Override
    public Item deleteItemById(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = basicDataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ITEM_FROM_ITEM_TABLE);
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            connection.commit();
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException e) {
                logger.error("Not able to rollback connection" + e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error("Deleting customer from database failed" + e.getMessage(), e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return null;
    }

    @Override
    public Item findItemByItemId(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Item item = new Item();
        try {
            connection = basicDataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_FROM_ITEM_TABLE);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item.setId(resultSet.getLong("id"));
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getBigDecimal("price"));
            item.setDescription(resultSet.getString("description"));
            connection.commit();
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException e) {
                logger.error("Not able to rollback connection" + e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error("SQL exception (request or table failed): " + e.getMessage() + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return item;
    }

    @Autowired
    public void setDataSource(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
}


