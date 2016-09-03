create database taxi;

create table car (id int primary key auto_increment, rego varchar(50));

alter table car 
 add make varchar(100);
 
#This master table store the name of all the income

create table income_type (id int primary key auto_increment, name varchar(100), 
 description varchar(300)); 
 

#This master table store the name of all the expensens 

create table expense_type (id int primary key auto_increment, name varchar(100),
 description varchar(300)); 