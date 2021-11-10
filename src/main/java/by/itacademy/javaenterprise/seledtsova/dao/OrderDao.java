package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import by.itacademy.javaenterprise.seledtsova.entity.Order;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    List<Order> getAll();

    void deleteOrderById(Integer orderId);

    public void update(int orderId, Order order);

}
