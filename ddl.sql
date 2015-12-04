DROP TABLE IF EXISTS shipping_address;
DROP TABLE IF EXISTS shipment;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS unit;
DROP TABLE IF EXISTS indent_details;
DROP TABLE IF EXISTS components;
DROP TABLE IF EXISTS product_version;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS indent;
DROP TABLE IF EXISTS trading_partners;


CREATE TABLE trading_partners
(
  id UUID PRIMARY KEY NOT NULL,
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

CREATE TABLE indent  (
  id UUID PRIMARY KEY NOT NULL,
  trade_partner_id UUID REFERENCES trading_partners(id),
  create_date TIMESTAMP NOT NULL,
  indent_value DECIMAL(7,2),
  notice VARCHAR(255)
);

CREATE TABLE product (
  id UUID PRIMARY KEY NOT NULL,
  code VARCHAR(100) NOT NULL,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE product_version (
  id UUID PRIMARY KEY NOT NULL,
  product_id REFERENCES product(id),
  type VARCHAR(50) NOT NULL,
  unit_id UUID REFERENCES unit(id)
);

CREATE TABLE  components (
  id UUID PRIMARY KEY NOT NULL,
  product_version_id UUID REFERENCES product_version(id)
);

CREATE TABLE indent_details (
  id UUID PRIMARY KEY NOT NULL,
  indent_id UUID REFERENCES indent(id),
  product_id UUID REFERENCES product_version(id),
  count int NOT NULL,
  unit_id UUID REFERENCES unit(id)
);

CREATE TABLE unit (
  id   UUID PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE payment (
  id UUID PRIMARY KEY NOT NULL,
  indent_id UUID REFERENCES indent(id),
  create_date TIMESTAMP NOT NULL,
  payment_type VARCHAR(64),
  payment_value DECIMAL(7,2)

);

CREATE TABLE shipment (
  id UUID PRIMARY KEY NOT NULL,
  indent_id UUID REFERENCES indent(id),
  shipment_type VARCHAR(64),
  shipment_value decimal(7,2)
);
CREATE TABLE shipping_address
(
  id UUID PRIMARY KEY NOT NULL,
  shipment_id UUID REFERENCES shipment(id),
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