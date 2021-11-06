package by.itacademy.javaenterprise.seledtsova.config;

import by.itacademy.javaenterprise.seledtsova.dao.CustomerDao;
import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class RunnerSpring {

    private final static Logger logger = LoggerFactory.getLogger(RunnerSpring.class);

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        OrderDao orderDao = context.getBean("orderDaoBean", OrderDaoImpl.class);
        logger.info("The bin orderDao class was created successfully");
        Order order = new Order();
        order.setOrderId(121311);
        order.setCustomerId(14787);
        order.setDateOrder("2021-09-21");
        orderDao.addOrder(order);
        System.out.println(orderDao.getAll());

        CustomerDao customerDao = context.getBean("customerDaoBean", CustomerDaoImpl.class);
        logger.info("The bin customerDao class was created successfully");
        Customer customer = new Customer();
        customer.setCustomerId(14788);
        customer.setFirstName("Vasia");
        customer.setLastName("Cannon");
        customerDao.addCustomer(customer);
        System.out.println(customerDao.getAll());

        context.close();
    }
}



