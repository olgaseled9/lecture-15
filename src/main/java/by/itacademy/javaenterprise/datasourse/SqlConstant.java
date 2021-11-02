package by.itacademy.javaenterprise.datasourse;

public interface SqlConstant {
    String SELECT_FROM_CUSTOMER_TABLE = "SELECT * FROM Customers ORDER BY last_name LIMIT 1 OFFSET 3";
    String SELECT_FROM_ORDER_TABLE = "SELECT * FROM Orders ORDER BY order_id LIMIT 1 OFFSET 1";
    String UPDATE_CUSTOMERS_TABLES_CUSTOMER = "UPDATE customers SET first_name='Polina' WHERE first_name='Julia'";
    String SELECT_FROM_CUSTOMER_TABLE_CUSTOMER_ID = "SELECT customer_id FROM Customers WHERE customer_id=?";
    String DELETE_CUSTOMER_FROM_CUSTOMER_TABLES = "DELETE FROM customers WHERE customer_id = ?";
    String ADD_CUSTOMER_TO_CUSTOMER_TABLES = "INSERT INTO customers VALUES (?,?,?);";
    String ADD_INTO_CUSTOMER_TABLES = "INSERT INTO customers (customer_Id, first_name, last_name) VALUES (28478,'Pavel','Pavlovich')";
    String ADD_INTO_ORDERS_TABLE = "INSERT INTO orders (order_Id, customer_Id, date_order) VALUE (11,25478,'27.10.2021')";
    String SELECT_FROM_ORDER_TABLE_CUSTOMER_ID = "SELECT * FROM Orders ORDER BY customer_id LIMIT 1 OFFSET 1";
}
