create database WEBPHONE
use WEBPHONE

create table Account(
IDA nvarchar(255),
IDuser varchar(50),
username nvarchar(255),
password nvarchar(255),
email nvarchar(255),
Role int,
constraint PK_IDA primary key (IDA)
);

create table CUSTOMER(
IDuser varchar(50),
Name nvarchar(50),
Dateb date,
Gender int,
Address_u nvarchar(255)
constraint PK_CUS primary key (IDuser)
);

create table PRODUCTs(
IDP varchar(50),
nameP nvarchar(255),
IDCate int,
Quantity int,
Amount int,
Damount int,
Desp nvarchar(255),
img nvarchar(255),
timer date,
constraint PK_Pro primary key (IDP)
);

create table CATEGORY(
IDC int,
nameC nvarchar(255),
constraint PK_CATE primary key(IDC)
);


create table ORDER_P(
IDO nvarchar(255),
IDAc nvarchar(255),
IDC varchar(50),
timer date,
total int,
ppm int,
process nvarchar(50),
constraint PK_ORDER primary key(IDO,IDAc,IDC)
);

create table ORDER_DETAIL(
IDO nvarchar(255),
IDP  varchar(50),
Amount int,
Cost int,
constraint PK_ORDERD primary key(IDO,IDP)

);

create table Cart(
IDCart varchar(50),
IDC varchar(50),
IDA nvarchar(255),
timerr date,
constraint PK_CART primary key (IDCart,IDC,IDA));
select * from Cart
CREATE TABLE CART_DETAIL(
STT int,
IDCart varchar(50),
IDP varchar(50),
Quantity int,
constraint PK_CARTDETAIL primary key (STT,IDCart,IDP));
--12/14/2024---

INSERT INTO account (IDA, IDuser, username, password, email, Role) VALUES
 ('A001', 'U001', 'user01', 'pass123', 'user01@example.com', 1),
('A002', 'U002', 'user02', 'pass456', 'user02@example.com', 2),
('A003', 'U003', 'user03', 'pass789', 'user03@example.com', 1),
('A004', 'U004', 'admin', 'pass123', 'trunghieuhsdd1@gmail.com', 1);

update Account set password ='1' where IDA ='A004'
use WEBPHONE

	INSERT INTO PRODUCTs VALUES
	(N'SP004',N'Iphone 14 max pro',1,10,189000,0,N'Ghi chu nay la sai nha ae',N'/assets/img/app1.webp',
	'2024-12-20'),
(N'SP001',N'Iphone 14 max pro',1,10,150000,10,N'Ghi chu nay la sai nha ae',N'/assets/img/app1.webp',
GETDATE()),
(N'SP002',N'Iphone 13 max pro',2,10,90000,10,N'Ghi chu nay la sai nha ae',N'/assets/img/app1.webp',
GETDATE()),
(N'SP003',N'Iphone 12 max pro',1,10,70000,40,N'Ghi chu nay la sai nha ae',N'/assets/img/app1.webp',
GETDATE())
 use webphone
select * from PRODUCTs
select * from account


--1= Nam,0= Nữ
INSERT INTO CUSTOMER VALUES
(N'U004',N'Triệu Trung Hiếu',CONVERT(DATETIME, '19-03-2004', 105),1,N'Cao Lộc-Lạng Sơn'),
(N'U001',N'Triệu Trung Hiếu',CONVERT(DATETIME, '19-03-2003', 105),1,N'Cao Lộc-Lạng Sơn'),
(N'U002',N'Lê Thế Long',CONVERT(DATETIME, '19-07-2004', 105),1,N'Hàm Nghi- Nghệ An'),
(N'U003',N'Nguyễn Vinh Quang',CONVERT(DATETIME, '19-03-2004', 105),1,N'Nghi Lộc-Thái Bình');
INSERT INTO CART VALUES
('C0001','U001','A001',GETDATE());
INSERT INTO CART_DETAIL VALUES
('U001',N'SP001', 1),
('U001',N'SP002', 1),
('U001',N'SP003', 1);
use WEBPHONE
alter table cart
add columns  id  IDENTITY(1,1)  PRIMARY KEY;
select * from Account;
select * from cart;
select * from CART_DETAIL;
SELECT * FROM PRODUCTS
