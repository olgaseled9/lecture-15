package by.itacademy.javaenterprise.dao;

import by.itacademy.javaenterprise.entity.Customer;

import java.util.List;

public interface CustomerService<K> {

    Customer addCustomer(Customer customer);

    List<Customer> getAll();

    void deleteCustomerById(Integer customerId);

    Customer findCustomerByCustomerId(Integer customerID);
}
