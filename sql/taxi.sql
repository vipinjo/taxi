create database taxi;

create table car (id int primary key auto_increment, rego varchar(50));

alter table car 
 add make varchar(100);
 
create table income_type (id int primary key auto_increment, name varchar(100), 
 description varchar(300)); 