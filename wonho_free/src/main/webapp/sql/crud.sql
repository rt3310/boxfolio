desc user;
desc post;

insert into user values('rt3310', '1234', '서원호', '1998-03-04', 'rt3310@naver.com');
insert into post(title, content, created, user_id, user_name) values('제목', '내용넝푸마ㅓㄴㅇ푸마ㅓ누파머처쥳차ㅠ넝차ㅓㅁ늋', now(), 'rt3310', '서원호');

select * from user;
select * from post;
select * from post where user_id='rt3310' and title='12';

delete from post;

alter table post modify column post_id int unsigned not null auto_increment;
alter table post add column `scrap` int unsigned not null default '0';

alter table post auto_increment=0;