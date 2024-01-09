create table product_info (
    product_id int primary key auto_increment,
    product_name varchar(50) not null,
    product_qty int not null,
     product_price double
);

create table order_info(
order_id int primary key auto_increment,
customer_name varchar(50) not null,
product_id int not null,
foreign key (product_id) references product_info(product_id),
product_qty int not null,
foreign key (product_id) references product_info(product_id),
total_amount double
);

DELIMITER //
CREATE PROCEDURE placeOrder(cname VARCHAR(20), pid INT, pQty INT)
BEGIN
    DECLARE pPrice DOUBLE;
    SELECT product_price INTO pPrice FROM product_info WHERE product_id = pid;
    INSERT INTO order_info(customer_name, product_id, product_qty, total_amount)
    VALUES(cname, pid, pQty, pPrice * pQty);
    UPDATE product_info SET product_qty = product_qty - pQty WHERE product_id = pid;
END //
DELIMITER ;
