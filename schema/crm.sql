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
(2, "cn2", "em2", "pn2", "sn2", "sn2", 222),
(3, "cn3", "em3", "pn3", "sn3", "sn3", 333);