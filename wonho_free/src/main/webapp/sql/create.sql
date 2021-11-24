create table user(
	id varchar(20) not null primary key,
    passwd varchar(20) not null,
    username varchar(20) not null,
    birth varchar(20) not null,
    email varchar(30) not null
);

create table post(
	post_id int(11) unsigned not null auto_increment primary key,
    title varchar(255) not null,
    content mediumtext not null default '',
    created datetime not null,
    user_id varchar(20) not null,
    user_name varchar(20) not null,
    view int(10) unsigned not null default '0',
    foreign key (user_id) references user(id)
);