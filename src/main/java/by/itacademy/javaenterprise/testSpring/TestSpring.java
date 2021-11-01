package by.itacademy.javaenterprise.testSpring;

import by.itacademy.javaenterprise.dao.CustomerService;
import by.itacademy.javaenterprise.dao.Impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestSpring {

    private final static Logger logger = LoggerFactory.getLogger(TestSpring.class);

    public static void main(String[] args) throws SQLException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        CustomerService customerService = context.getBean("CustomerServiceImpl", CustomerServiceImpl.class);

        context.close();
    }
}

