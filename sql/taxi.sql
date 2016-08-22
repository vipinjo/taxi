create database taxi;

create table car (id int primary key auto_increment, rego varchar(50));

alter table car 
 add make varchar(100);