DROP DATABASE IF EXISTS crm;
CREATE DATABASE crm;
USE crm;

CREATE TABLE customer (
  id BIGINT AUTO_INCREMENT NOT NULL,
   customer_name VARCHAR(100) NULL,
   email VARCHAR(100) NULL,
   phone_number VARCHAR(20) NULL,
   street_name VARCHAR(50) NULL,
   street_number VARCHAR(50) NULL,
   postal_code INT NULL,
   CONSTRAINT pk_customer PRIMARY KEY (id)
);

INSERT INTO customer VALUES
(1, "cn1", "em1", "pn1", "sn1", "sn1", 11),
(2, "cn2", "em2", "pn2", "sn2", "sn2", 222),
(3, "cn3", "em3", "pn3", "sn3", "sn3", 333);

CREATE TABLE sale (
  id BIGINT AUTO_INCREMENT NOT NULL,
   product_name VARCHAR(50) NULL,
   quantity_sold INT NULL,
   transaction_date date NULL,
   customer_id BIGINT NULL,
   CONSTRAINT pk_sale PRIMARY KEY (id)
);

ALTER TABLE sale ADD CONSTRAINT FK_SALE_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);

INSERT INTO sale VALUES
(1, "product 1", 2, "2024-04-15", 2);