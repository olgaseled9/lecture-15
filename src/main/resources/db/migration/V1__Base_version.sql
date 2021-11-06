CREATE TABLE IF NOT EXISTS `customers`
(
    `customer_id` INT
(
    11
) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR
(
    25
) NOT NULL,
    `last_name` VARCHAR
(
    30
) NOT NULL,
    PRIMARY KEY
(
    customer_id
))
    ENGINE=INNODB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=32

CREATE TABLE IF NOT EXISTS `orders`
(
    `order_id` INT
(
    11
) NOT NULL AUTO_INCREMENT,
    `customer_id` INT
(
    11
) NOT NULL Unique,
    `date_order` VARCHAR
(
    30
) NOT NULL,
    PRIMARY KEY
(
    order_id
))

CREATE TABLE IF NOT EXISTS `orders`
(
    `order_id` INT
(
    11
) NOT NULL AUTO_INCREMENT,
    `customer_id` INT
(
    11
) NOT NULL Unique,
    `date_order` VARCHAR
(
    30
) NOT NULL,
    PRIMARY KEY
(
    order_id
))
    INSERT INTO `Orders`
(
    `order_id`,
    `customer_id`,
    `date_order`
) VALUES
(
    123,
    1,
    '2021-09-15'
),
(
    124,
    2,
    '2021-09-16'
),
(
    125,
    3,
    '2021-09-17'
),
(
    126,
    4,
    '2021-09-21'
),
(
    127,
    6,
    '2021-09-25'
),
(
    128,
    5,
    '2021-09-29'
),
(
    129,
    9,
    '2021-10-01'
),
(
    130,
    7,
    '2021-10-15'
),
(
    131,
    8,
    '2021-10-16'
),
(
    132,
    10,
    '2021-10-18'
);

INSET
INTO `Order_product` (`order_id`, `product_id`, `quantity`) VALUES
(123, '200830', 1),
(124, '200839', 2),
(125, '56384', 4),
(126, '57878', 4),
(127, '54759', 1),
(128, '47854', 2),
(129, '46125', 1),
(130, '24578', 1),
(131, '21121', 5),
(132, '12345', 1);

INSERT INTO `Products` (`product_id`, `product_name`, `product_size`)
VALUES ('200839', 'Frame Pool', '244*76'),
       ('56384', 'Frame Pool', '457*122'),
       ('57878', 'Frame Pool', '460*120'),
       ('54759', 'Inflatable mattress', '76*191*25'),
       ('47854', 'Inflatable mattress', '152*203*25'),
       ('46125', 'Inflatable mattress', '157*88*18'),
       ('24578', 'Inflatable mattress', '157*88*10'),
       ('21121', 'Inflatable bed', '152*200*15'),
       ('12345', 'Inflatable bed', '140*190*15'),
       ('12346', 'Inflatable bed', '120*200*25');
)

