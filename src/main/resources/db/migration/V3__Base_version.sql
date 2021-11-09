create table if not exists Order_product
(
    order_id   SERIAL  not null primary key,
    product_id VARCHAR not null UNIQUE,
    quantity   int     not null,
    FOREIGN KEY (order_id) REFERENCES "Orders" (order_id)
);