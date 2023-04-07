CREATE DATABASE IF NOT EXISTS SHOP_USER;

USE SHOP_USER;
drop table if exists users cascade;

create table users
(
    id         serial,
    username   varchar(255) not null,
    password   varchar(255) not null,
    is_enabled boolean      not null,
    primary key (id)
);
