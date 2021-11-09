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

        CustomerDao customerDao = context.getBean("customerDaoBean", CustomerDaoImpl.class);
        logger.info("The bin customerDao class was created successfully");
        customerDao.addCustomer(new Customer(26, "Vasia", "Petrov"));
        System.out.println(customerDao.getAll());
        System.out.println(customerDao.findCustomerByCustomerId(9));

        OrderDao orderDao = context.getBean("orderDaoBean", OrderDaoImpl.class);
        logger.info("The bin orderDao class was created successfully");
        orderDao.addOrder(new Order(26, 26, "2020-11-09"));
        orderDao.deleteOrderById(26);
        customerDao.deleteCustomerById(26);
        System.out.println(orderDao.getAll());

        context.close();
    }
}




