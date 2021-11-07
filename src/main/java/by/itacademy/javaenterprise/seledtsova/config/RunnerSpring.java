package by.itacademy.javaenterprise.seledtsova.config;

import by.itacademy.javaenterprise.seledtsova.dao.CustomerDao;
import by.itacademy.javaenterprise.seledtsova.dao.OrderDao;
import by.itacademy.javaenterprise.seledtsova.dao.impl.CustomerDaoImpl;
import by.itacademy.javaenterprise.seledtsova.dao.impl.OrderDaoImpl;
import by.itacademy.javaenterprise.seledtsova.entity.Customer;
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
        Customer customer = new Customer();
        customer.setCustomerId(16);
        customer.setFirstName("Vasia");
        customer.setLastName("Cannonov");
        customerDao.addCustomer(customer);
        System.out.println(customerDao.getAll());
        System.out.println(customerDao.findCustomerByCustomerId(16));

        OrderDao orderDao = context.getBean("orderDaoBean", OrderDaoImpl.class);
        logger.info("The bin orderDao class was created successfully");
        System.out.println(orderDao.findOrderByCustomerId(10));

        context.close();
    }
}



