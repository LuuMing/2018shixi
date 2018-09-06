create database if not exists LMS;
use LMS;
drop table if exists users;
drop table if exists books;
create table users (
	id int auto_increment,
	account varchar(100) not null,
	name varchar(100) not null,
	sex varchar(4) not null,
	pwd varchar (20) not null,
	usr_type int,
	begDat date,
	endDat date,
	maxCap int,
	cnt int,
	log varchar(100),
	primary key(id)
);
insert into users(account,name,sex,pwd) values("151004113","卢明","男","2222");

create table books
(
	id int auto_increment,
	name varchar(100),
	author varchar(100),
	cnt int,
	primary key(id)
);
insert into books(name,author,cnt) values("考研数学","张宇","3");
