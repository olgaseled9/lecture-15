CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(25) NOT NULL,
    `last_name` VARCHAR(30) NOT NULL,
    PRIMARY KEY (customer_id))


CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) NOT NULL Unique,
    `date_order` VARCHAR(30) NOT NULL,
    PRIMARY KEY (order_id))

CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) NOT NULL Unique,
    `date_order` VARCHAR(30) NOT NULL,
    PRIMARY KEY (order_id))

    ENGINE=INNODB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
)
