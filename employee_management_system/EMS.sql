create database employeemanagementsystem;
Use employeemanagementsystem;
drop database employeemanagementsystem;

create table login(username varchar(20), password varchar(20));

insert into login values('admin', '12345');

create table employee(name varchar(20), fname varchar(20), dob varchar(30), salary varchar(20), address varchar(100), phone varchar(20), email varchar(40), education varchar(20), designation varchar(30), aadhar varchar(25), empId varchar(15));

select*from employee;