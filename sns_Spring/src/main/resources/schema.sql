drop database sns_spring;
create database sns_spring;

use sns_spring;

create table user(
user_id int not null auto_increment,
password varchar(16) not null,
user_name varchar(16) not null unique,
created_at datetime not null,
updated_at datetime ,
primary key(user_id)
);


create table content(
date_id int not null auto_increment,
user_name varchar(16) not null ,
message varchar(255) not null,
created_at datetime not null,
updated_at datetime ,
primary key(date_id)
);