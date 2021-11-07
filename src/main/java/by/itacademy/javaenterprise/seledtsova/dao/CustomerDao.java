package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Customer;

import java.util.List;


public interface CustomerDao<K> {

    void addCustomer(Customer customer);

    List<Customer> getAll();

    void deleteCustomerById(Integer customerId);

    Customer findCustomerByCustomerId(Integer customerID);

    public void update (int customerId, Customer customer);
}
