package by.itacademy.javaenterprise.dao;

import by.itacademy.javaenterprise.entity.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    List<Order> getAll();

    void deleteOrderById(Integer orderId);


}
