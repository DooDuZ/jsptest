drop database if exists bluemarble;
create database bluemarble;
use bluemarble;
drop table if exists member;
create table member(
	m_no int auto_increment,			-- 회원 번호
    m_id varchar(20),					-- 아이디
    m_password varchar(15),				-- 비밀번호
    m_email varchar(30),				-- 이메일
    m_point int default 0,				-- 보유 포인트
    m_nick varchar(10),					-- 닉네임
    m_img text,							-- 캐릭터 이미지
    m_profile text,						-- 자기소개
    wins int default 0,					-- 1등 횟수
    total int default 0,				-- 총 게임 횟수'
    constraint m_no_pk primary key(m_no)
);

drop table if exists board;
create table board(
	b_no int auto_increment,		-- 게시물 번호
	b_title varchar(30),			-- 게시물 제목
    b_content longtext,				-- 내용
    b_file text,  					-- 첨부파일
    b_date datetime default now(),	-- 작성날짜
    m_no int,						-- 작성 회원 번호
    constraint b_no_pk primary key(b_no),
	constraint bmno_fk foreign key(m_no) references member(m_no)
);

drop table if exists comment;
create table comment(
	c_no int auto_increment, 		-- 댓글 번호
    c_content text,					-- 댓글 내용
    c_date datetime default now(),  -- 작성 시간
    c_index smallint ,				-- 대댓글 구분용
    b_no int,						-- 게시물 번호
    m_no int,						-- 작성 회원 번호
	constraint c_no_pk primary key(c_no),
    constraint cbno_fk foreign key(b_no) references board(b_no),
	constraint cmno_fk foreign key(m_no) references member(m_no)
    
);

select * from comment;