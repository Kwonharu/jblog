
drop table users;
drop table blog;
drop table category;
drop table post;
drop table comments;
drop SEQUENCE seq_users_no;
drop SEQUENCE seq_category_no;
drop SEQUENCE seq_post_no;
drop SEQUENCE seq_comments_no;


--table 생성

create table users(
    userNo      NUMBER,
    id          varchar2(50)    not null    UNIQUE,
    userName    varchar2(100)   not null,
    password    varchar2(50)    not null,
    joinDate    date    not null,
    PRIMARY key(userNo)
);

create table blog(
    id          varchar2(50),
    blogTitle   varchar2(200)   not null,
    logoFile    varchar2(200),
    PRIMARY key(id),
    constraint blog_fk foreign key (id) references users(id)
--   제약      샌즈     FK  (내 테이블 컬럼)  참조   참조할 테이블(칼럼)
);

create table category(
    cateNo       NUMBER,
    id           varchar2(50),
    cateName     varchar2(200)   not null,
    description  varchar2(500),
    regDate      date    not null,
    PRIMARY key(cateNo),
    constraint category_fk foreign key (id) references blog(id)
);

create table post(
    postNo       NUMBER,
    cateNo       NUMBER,
    postTitle    varchar2(300)   not null,
    postContent  varchar2(4000),
    regDate      date    not null,
    PRIMARY key(postNo),
    constraint post_fk foreign key (cateNo) references category(cateNo)
);

create table comments(
    cmtNo       NUMBER,
    postNo      NUMBER,
    userNo      NUMBER,
    cmtContent  varchar2(1000)    not null,
    regDate     date    not null,
    PRIMARY key(cmtNo),
    constraint comments_postNo_fk foreign key (postNo) references post(postNo),
    constraint comments_userNo_fk foreign key (userNo) references users(userNo)
);


--sequence 생성

CREATE SEQUENCE seq_users_no
INCREMENT BY 1
START WITH 1
NOCACHE;

CREATE SEQUENCE seq_category_no
INCREMENT BY 1
START WITH 1
NOCACHE;

CREATE SEQUENCE seq_post_no
INCREMENT BY 1
START WITH 1
NOCACHE;

CREATE SEQUENCE seq_comments_no
INCREMENT BY 1
START WITH 1
NOCACHE;



--select user
select  id,
        userName
from users
where id = ''
and password = '';


--user insert
insert into users
values(seq_users_no.nextval, 'sua', '수아', '0314', sysdate);


--user update
update users
set userName = '세은'
where userName = 'asd';



--회원가입 시 blog 제목 insert
insert into blog
values('asd', '세은의 블로그입니다.', 'spring-logo.jpg');



--select blog
select  id,
        blogTitle,
        logoFile
from blog
where id = 'sua';


--update blog
update blog
set blogTitle = '냠냠굳',
    logoFile = '읎서요'
where id = 'aaa';







delete users
where userNo = 7;

delete blog
where id = 'a';




select *
from users;

select *
from blog;


commit;
rollback;


