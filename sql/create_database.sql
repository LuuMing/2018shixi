drop database if exists LMS;
create database if not exists LMS charset utf8;
use LMS;
create table users (
	id int auto_increment,
	user_account varchar(15) not null,
	user_name varchar(15) not null,
	sex varchar(4) not null,
	pwd varchar (20) not null,
	usr_type varchar(20),
	begDat date,
	endDat date,
	maxCap int,
	cnt int,
	log varchar(100),
	primary key(id)
);

insert into users(user_account,user_name,sex,pwd,usr_type) values ("151004113","卢明","男","151004113","管理员");


create table books
(
	id int auto_increment,
	name varchar(20) not null, 
	author varchar(50) not null,
	primary key(id)
);
create table book_type
(
	id int auto_increment,
	type_name varchar(50),
	primary key(id)
);
insert into book_type(id,type_name) values(01,"考研类");
insert into books(name,author) values ("考研数学","张宇");


create table lend
(
         id int auto_increment,
         user_id int not null	,
         book_id int not null,
         ldDat date,
         endDat date,
	 bakDat date,
	 expired bool,
         log varchar(100),
         primary key(id),
	 foreign key (user_id) references users(id),	
	 foreign key (book_id) references books(id)
);


insert into lend(user_id,book_id,ldDat,endDat) values(151004113,01,"2018-9-6","2018-9-10");

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


create table admin_users
(
    id int auto_increment,
    admin_account varchar(15) not null,
    user_account varchar(15) not null,
    lend_return varchar(4),
    primary key(id)
);
 
insert into admin_users(admin_account,user_account) values("admin0001","jdk12503");   
