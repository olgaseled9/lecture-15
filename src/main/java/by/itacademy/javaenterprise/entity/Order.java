package by.itacademy.javaenterprise.entity;


import java.util.List;

public class Order {

    private Integer orderId;
    private Integer CustomerId;
    private String dateOrder;
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", CustomerId=" + CustomerId +
                ", dateOrder='" + dateOrder + '\'' +
                ", customers=" + customers +
                '}';
    }
}
