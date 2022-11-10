create table users(
id varchar2(12) primary key,
password varchar2(20) not null,
--2차 비밀번호(확인용)
name varchar2(12) not null,
phone varchar2(20)
--nickname
)

insert into users values('admin','admin123','관리자','010-1111-2222');
insert into users values('guest','guest123','손님','010-2222-3333');
insert into users values('test','test123','테스트','010-3333-4444');

select * from users
delete from users
drop table users



create table content(
contentcode number(38) primary key, --코드
content varchar2(3) not null,	--제품종류
contentname varchar2(30) not null,	--제품이름
publisher varchar2(15),	--출판사
publicationdate varchar2(15),	--발행일
rentaldate varchar2(15),	--대여일
returndate varchar2(15),	--반납일
price number(8) not null,	--가격
cnt number(10) default 0
)

insert into content(contentcode,content,contentname,publisher,publicationdate,price)
values(((select nvl(max(contentcode)+1,000001) from content)),'n','가우스','동아','2010-01-02',900);
insert into content(contentcode,content,contentname,publisher,publicationdate,price)
values(((select nvl(max(contentcode)+1,000001) from content)),'c','그리스로마신화','동아','2010-01-02',500);
insert into content(contentcode,content,contentname,publisher,publicationdate,price)
values(((select nvl(max(contentcode)+1,000001) from content)),'d','타이타닉','동아','2010-01-12',1200);

select * from 
(select rownum as rnum,c.* from
(select contentcode,content,contentname,publisher,publicationdate,rentaldate,returndate,price from content order by contentcode desc)
c)
where rnum between ? and ?;

select * from (select rownum as rnum,c.* from(select contentcode,content,contentname,publisher,publicationdate,rentaldate,returndate,price from content order by contentcode desc)c) where rnum between ? and ?;

select * from content
delete from content
drop table content

create table inout(
id varchar2(12),
name varchar2(12) not null,
contentcode number(38),
contentname varchar2(30) not null,
content varchar2(3) not null,
rentaldate varchar2(15),
returndate varchar2(15),
price number(8) not null,
primary key(id,contentcode)
)
--where절 사용해서 id값을 이용해서 users의 상세정보 볼 때, 빌린 내역 리스트화 하기.

select u.id, u.name, c.contentcode, c.contentname, c.content, c.rentaldate, c.returndate, c.price
from users u, content c, inout i
where u.id=i.id and c.contentcode=i.contentcode
order by i.rentaldate desc;








