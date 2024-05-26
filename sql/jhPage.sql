--================================
-- 관리자계정 - dagachi 계정 생성
--================================
--alter session set "_oracle_script" = true;
--
--create user jhPage
--identified  by jhPage
--default tablespace users;
--
--alter user dagachi quota unlimited on users;
--
--grant connect, resource to jhPage;

--=================초기화 블럭======
--drop table member;

------------------------------------------------- 테이블 -------------------------------------------------
create table member (
	member_id	varchar2(30) not null,
	password	varchar2(150) not null,
	name	 varchar2(20) not null,
    nickname varchar2(30) not null,
	email	varchar2(50) not null
);

insert into member values('k','1','김준한','김준한','k0j4h25@hanmail.net');

select * from member;