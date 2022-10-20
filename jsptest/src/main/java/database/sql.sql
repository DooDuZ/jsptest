create database jsptest;
use jsptest;

create table board(
	cNo int primary key auto_increment,
	title varchar(50),
    content longtext,
    writer varchar(30),
    password varchar(20),
    cdate datetime,
    cView int
);

