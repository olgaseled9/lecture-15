package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Customer;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface CustomerDao<K> {

    Customer addCustomer(Customer customer);

    List<Customer> getAll();

    void deleteCustomerById(Integer customerId);

    Customer findCustomerByCustomerId(Integer customerID);
}
