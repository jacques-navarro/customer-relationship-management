DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS sale CASCADE;

CREATE TABLE customer (
    id bigserial PRIMARY KEY,
    customer_name varchar(25),
    email varchar(50),
    phone_number varchar(20),
    street_name varchar(50),
    street_number varchar(50),
    postal_code integer
);

INSERT INTO customer (id, customer_name, email, phone_number, street_name, street_number, postal_code)
VALUES  (1, 'cn1', 'em1', 'pn1', 'sn1', 'sn1', 11),
        (2, 'cn2', 'em2', 'pn2', 'sn2', 'sn2', 222),
        (3, 'cn3', 'em3', 'pn3', 'sn3', 'sn3', 333);

CREATE TABLE sale (
    id bigserial PRIMARY KEY,
    product_name varchar(25),
    quantity_sold integer,
    transaction_date date,
    customer_id integer,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

INSERT INTO sale (id, product_name, quantity_sold, transaction_date, customer_id)
VALUES  (1, 'product 1', 2, '2024-04-15', 2);