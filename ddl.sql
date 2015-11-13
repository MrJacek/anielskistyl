DROP TABLE IF EXISTS trading_partners;
CREATE TABLE trading_partners
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    city VARCHAR(255) NOT NULL,
    contry VARCHAR(255) NOt NULL,
    postal_kode VARCHAR(32) NOT NULL,
    address_type VARCHAR(20) NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL
);
DROP TABLE IF EXISTS indent;
CREATE TABLE indent  (
    id SERIAL PRIMARY KEY NOT NULL,
    trade_partner_id SERIAL REFERENCES trading_partners(id),
    create_date TIMESTAMP NOT NULL,
    indent_value DECIMAL(7,2),
    notice VARCHAR(255)
);
DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
    id SERIAL PRIMARY KEY NOT NULL,
    indent_id SERIAL REFERENCES indent(id),
    create_date TIMESTAMP NOT NULL,
    payment_type VARCHAR(64),
    payment_value DECIMAL(7,2)

);
DROP TABLE IF EXISTS shipment;
CREATE TABLE shipment (
    id SERIAL PRIMARY KEY NOT NULL,
    indent_id SERIAL REFERENCES indent(id),
    shipment_type VARCHAR(64),
    shipment_value decimal(7,2)
);
DROP TABLE IF EXISTS shipping_address;
CREATE TABLE shipping_address
(
    id SERIAL PRIMARY KEY NOT NULL,
    shipment_id SERIAL REFERENCES shipment(id),
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    city VARCHAR(255) NOT NULL,
    contry VARCHAR(255) NOt NULL,
    postal_kode VARCHAR(32) NOT NULL,
    address_type VARCHAR(20) NOT NULL,
    phone VARCHAR(50)
);