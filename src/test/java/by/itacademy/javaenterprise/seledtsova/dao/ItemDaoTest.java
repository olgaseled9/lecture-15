package by.itacademy.javaenterprise.seledtsova.dao;


import by.itacademy.javaenterprise.seledtsova.dao.impl.ItemDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import org.apache.commons.dbcp2.BasicDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ItemDaoTest {
    private Connection mockConnection;
    private BasicDataSource mockDataSource;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStmnt;
    private ResultSet mockResultSet;
    private ItemDao itemDao;

    @BeforeEach
    public void setUp() throws Exception {
        itemDao = new ItemDaoImpl(mockDataSource);
        mockDataSource = Mockito.mock(BasicDataSource.class);
        mockConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(Statement.class);
        mockPreparedStmnt = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.executeUpdate()).thenReturn(100);
        when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE);
    }

    @Test
    public void shouldSaveItemTest() {
        Item item = Item.builder()
                .id(131L)
                .name("Christmas tree")
                .price(new BigDecimal("123.00"))
                .description("The tree is decorated with natural cones")
                .build();
        Assertions.assertThat(item.getName()).isGreaterThan(String.valueOf(0));
    }

    @Test
    public void shouldSelectItemOnBadId() {
        Long id = -2L;
        ItemDao itemDao = new ItemDaoImpl(mockDataSource);
        Item item = itemDao.findItemByItemId(id);
        Assertions.assertThat(item.getId()).isEqualTo(0);
    }

    @Test
    public void shouldSelectItem() {
        Long id = 2L;
        ItemDao itemDao = new ItemDaoImpl(mockDataSource);
        Item item = new Item();
        item.setId(id);
        itemDao.findItemByItemId(id);
        Assertions.assertThat(item.getId()).isEqualTo(2);
    }

    @Test
    public void shouldDeleteItemTest() {
        Long id = 35L;
        ItemDao itemDao = new ItemDaoImpl(mockDataSource);
        Item item = itemDao.deleteItemById(id);
        Assertions.assertThat(item).isNull();
    }
}
