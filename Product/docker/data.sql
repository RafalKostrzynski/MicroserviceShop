CREATE DATABASE IF NOT EXISTS SHOP_PRODUCT;

USE SHOP_PRODUCT;
drop table if exists product cascade;

create table product
(
    id       serial,
    name     varchar(255)   not null,
    price    numeric(38, 2) not null,
    quantity integer        not null,
    primary key (id)
);
