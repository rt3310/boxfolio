create table user(
	id varchar(20) not null primary key,
    passwd varchar(20) not null,
    username varchar(20) not null,
    birth varchar(20) not null,
    email varchar(30) not null
);

create table post(
	id int unsigned not null primary key auto_increment,
    title varchar(255) not null,
    content mediumtext,
    created datetime not null,
    user_id varchar(20) not null,
    user_name varchar(20) not null,
    views int unsigned not null default '0',
    likes int unsigned not null default '0',
    scraps int unsigned not null default '0',
    replys int unsigned not null default '0',
    foreign key(user_id) references user(id)
);

create table reply(
	id int unsigned not null primary key auto_increment,
    content text,
    created datetime not null,
    post_id int unsigned not null,
    user_id varchar(20) not null,
    user_name varchar(20) not null,
    foreign key(post_id) references post(id),
    foreign key(user_id) references user(id)
);

create table likes(
	id int unsigned not null primary key auto_increment,
    user_id varchar(20) not null,
    post_id int unsigned not null,
    foreign key(user_id) references user(id),
    foreign key(post_id) references post(id)
);

create table scraps(
	id int unsigned not null primary key auto_increment,
    user_id varchar(20) not null,
    post_id int unsigned not null,
    foreign key(user_id) references user(id),
    foreign key(post_id) references post(id)
);

create table portfolio(
	id int unsigned not null primary key auto_increment,
    title varchar(255) not null,
    content mediumtext,
    user_id varchar(20) not null,
    foreign key(user_id) references user(id)
);

insert into user(id, passwd, username, birth, email) values('abc', '1234', '홍길동', '1998-03-04', 'abc1234@naver.com');
insert into post(title, content, created, user_id, user_name) values('게시판 예제입니다.', '<b>게시판</b> <l>예제</l> 내용입니다.', now(), 'abc', '홍길동');
insert into reply(content, created, post_id, user_id, user_name) values('댓글 예제입니다.', now(), 1, 'abc', '홍길동');
insert into portfolio(title, content, user_id) values('포트폴리오 예제입니다.', '<div class="pofol-board"><input type="file" accept="image/*" style="display: none;"><img class="image" src="blob:http://localhost:8080/5e261eb6-52b2-4a02-9f7b-638ecd7828d3" style="width: auto; height: 50%; object-fit: contain;"></div><div class="pofol-board" contenteditable="true">나의 포트폴리오</div><div class="pofol-board" contenteditable="true">소스코드</div>', 'abc');

drop table user;
drop table post;
drop table reply;
drop table likes;
drop table scraps;
drop table portfolio;