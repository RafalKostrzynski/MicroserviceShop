CREATE DATABASE IF NOT EXISTS SHOP_SHIPMENT;

USE SHOP_SHIPMENT;

drop table if exists shipment_courier cascade;
drop table if exists shipment_region cascade;

create table shipment_courier
(
    id             serial,
    courier        varchar(255)   not null,
    courier_margin numeric(38, 2) not null,
    primary key (id)
);

create table shipment_region
(
    id              serial,
    region          varchar(255)   not null,
    shipment_margin numeric(38, 2) not null,
    primary key (id)
);

