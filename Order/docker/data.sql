CREATE DATABASE IF NOT EXISTS SHOP_ORDERS;

USE SHOP_ORDERS;

drop table if exists orders cascade;

create table orders
(
    id                    serial,
    shipment_courier_name varchar(255)   not null,
    shipment_region       varchar(255)   not null,
    total_price           numeric(38, 2) not null,
    username              varchar(255)   not null,
    primary key (id)
);
