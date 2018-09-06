create database if not exists LMS charset utf8;
use LMS;
drop table if exists users;
drop table if exists books;
drop table if exists lend;
drop table if exists admin;
drop table if exists admin_users;
drop table if exists admin_books;
create table users (
	id int auto_increment,
	user_account varchar(15) not null,
	user_name varchar(15) not null,
	sex varchar(4) not null,
	pwd varchar (20) not null,
	usr_type int,
	begDat date,
	endDat date,
	maxCap int,
	cnt int,
	log varchar(100),
	primary key(id)
) ;

insert into users(user_account,user_name,sex,pwd) values ("151004113","卢明","男","151004113");

select * from users;

create table books
(
	id int auto_increment,
  book_index varchar(15) not null,
	book_name varchar(20) not null,
	author varchar(50) not null,
	cnt int not null,
	primary key(id)
);

insert into books(book_index,book_name,author,cnt) values ("jdk12503","考研数学","张宇","3");

select * from books;

create table lend
(
         id int auto_increment,
         user_account varchar(15) not null,
         book_index varchar(15) not null,
         user_name varchar(15) not null,
         book_name varchar(50) not null,
         ldDat date,
         reDat date,
         reLend varchar(4),
         log varchar(100),
         primary key(id)
);


insert into lend(user_account,book_index,user_name,book_name) values("151004113","jdk12503","卢明","考研数学");

select * from lend;

create table admin
(
     id int auto_increment,
     admin_account varchar(15) not null,
     admin_psw varchar(15) not null,
     sex varchar(4) not null,
     phone_number varchar(15),
     address varchar(15),
     log varchar(100),
     primary key(id)
);


insert into admin(admin_account,admin_psw,sex) values("admin0001","zhaoke","男");

select * from admin;

create table admin_books 
(
    id int auto_increment,
    admin_account varchar(15) not null,
    book_index varchar(15) not null,
    delDat date,
    online varchar(4),
    primary key(id)
);

insert into admin_books(admin_account,book_index) values("admin0001","jdk12503");

select * from admin_books;

create table admin_users
(
    id int auto_increment,
    admin_account varchar(15) not null,
    user_account varchar(15) not null,
    lend_return varchar(4),
    primary key(id)
);
 
insert into admin_users(admin_account,user_account) values("admin0001","jdk12503");   

select * from admin_users;
     
