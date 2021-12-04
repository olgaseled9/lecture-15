package by.itacademy.javaenterprise.seledtsova;

import by.itacademy.javaenterprise.seledtsova.config.SpringConfig;
import by.itacademy.javaenterprise.seledtsova.dao.CustomerDao;
import by.itacademy.javaenterprise.seledtsova.dao.ItemDao;
import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.ItemDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import by.itacademy.javaenterprise.seledtsova.entity.Item;
import by.itacademy.javaenterprise.seledtsova.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        CustomerDao customerDao = context.getBean("customerDaoBean", CustomerDaoImpl.class);
        logger.info("The bin customerDao class was created successfully");
        OrderDao orderDao = context.getBean("orderDaoBean", OrderDaoImpl.class);
        logger.info("The bin orderDao class was created successfully");
        logger.info("{}", orderDao.getAll());
        logger.info("{}", orderDao.findOrderById(123L));
        customerDao.saveCustomer(new Customer(25L, "Vasia", "Petrov"));
        logger.info("Customer are added successfully");
        orderDao.saveOrder(new Order(25L, 25L, 100));
        logger.info("Order are added successfully");
        orderDao.deleteOrderById(25L);
        logger.info("Order delete successful");
        customerDao.deleteCustomerById(25L);
        logger.info("Customer delete successful");
        logger.info("{}", customerDao.findCustomerByCustomerId(9L));
        ItemDao itemDao = context.getBean("itemDaoBean", ItemDaoImpl.class);
        logger.info("{}", itemDao.getAll());
        logger.info("{}", itemDao.findItemByItemId(4L));
        Item item = new Item(131L, "Christmas tree", new BigDecimal(125.00), "The tree is decorated with natural cones");
        logger.info("{}", itemDao.saveItem(item));
        itemDao.deleteItemById(131L);
        logger.info("{}", itemDao.getAll());

        context.close();
    }
}





