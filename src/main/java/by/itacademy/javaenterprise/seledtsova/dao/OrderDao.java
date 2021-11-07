package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Order;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    List<Order> getAll();

    void deleteOrderById(Integer orderId);

    Order findOrderByCustomerId(Integer customerID);

    public void update(int orderId, Order order);
}
