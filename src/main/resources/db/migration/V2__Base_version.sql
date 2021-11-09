create table if not exists Orders
(
    order_id SERIAL not null primary key,
    customer_id serial not null Unique,
    "date_order" date   not null,
    foreign key (customer_id) references Customers (customer_id)
);
