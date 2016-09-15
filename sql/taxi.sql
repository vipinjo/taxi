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
 
create table driver (id int primary key auto_increment, family_name varchar(100),
    given_name varchar(100), dc varchar(50), abn varchar(50)); 
    
create table shift_report (id int primary key auto_increment, 
    report_date DATETIME, finish_date DATETIME, 
    car_id int, driver_id int, start_meter_reading int,
    end_meter_reading int, meter_rev decimal(10,2), 
    owner_rev decimal(10,2), owner_subsidy decimal(10,2), bailment_fee decimal(10, 2),
    paper_voucher decimal(10, 2), fuel_receipt decimal(10, 2), online_receipt decimal(10, 2),
    total decimal(10, 2),
    foreign key (driver_id) references driver(id),
    foreign key (car_id) references car(id));  