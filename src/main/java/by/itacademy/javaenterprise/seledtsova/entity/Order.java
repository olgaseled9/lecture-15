package by.itacademy.javaenterprise.seledtsova.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Order {

    private Integer orderId;
    private Integer CustomerId;
    private String dateOrder;
    private List<Customer> customers;
}
