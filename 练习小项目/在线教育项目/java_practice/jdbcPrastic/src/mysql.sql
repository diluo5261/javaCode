-- 创建学科
create table if not exists category(
    id INT(11) primary key auto_increment,
    name varchar(20)

    );

insert into category values(null,"历史");
insert into category values(null,"艺术");
insert into category values(null,"计算机");
insert into category values(null,"数学");
insert into category values(null,"小说");

select* from category;


-- 创建 student 类

create table if not exists student(
    id int(11) primary key auto_increment,
    name varchar(20)
    );

insert into student values(null,"王昭君");
insert into student values(null,"李白");
insert into student values(null,"貂蝉");
insert into student values(null,"小乔");
insert into student values(null,"韩信");

select * from student;


-- 创建 book 表
create table if not exists book(
    id int(10) primary key auto_increment,
    name varchar(20),
    author varchar(20),
    price decimal(10,2),
    category_log int(11),
    constraint fk_book_category_id foreign key (category_log) references category(id)
    );

INSERT INTO book VALUES (1, '深入理解Java虚拟机', '周志明', 57.90, 3);
INSERT INTO book VALUES (2, '西游记', '吴承恩', 30.68, 5);
INSERT INTO book VALUES (3, '儒林外史', '吴敬梓', 18.80, 5);
INSERT INTO book VALUES (4, '聊斋志异', '蒲松龄', 21.00, 5);
INSERT INTO book VALUES (5, '史记', '司马迁', 278.20, 1);
INSERT INTO book VALUES (6, '资治通鉴', '司马光', 524.00, 1);
INSERT INTO book VALUES (7, 'Java核心技术 卷I：基础知识', 'Cay S. Horstmann', 92.50, 3);
INSERT INTO book VALUES (8, 'Java核心技术卷II：高级特性', 'Cay S. Horstmann', 111.20, 3);
INSERT INTO book VALUES (9, 'Java多线程编程核心技术', '高洪岩', 47.50, 3);
INSERT INTO book VALUES (10, '诗经', '孔子', 22.00, 2);
INSERT INTO book VALUES (11, '唐诗三百首', '蘅塘居士', 49.30, 2);
INSERT INTO book VALUES (12, '唐诗三百首', '蘅塘居士', 55.00, 2);
INSERT INTO book VALUES (13, '西游记', '吴承恩', 47.50, 5);
INSERT INTO book VALUES (14, '唐诗三百首', '蘅塘居士', 56.50, 2);

select * from book;


create table if not exists borrow_info(
    id int(11) primary key auto_increment,
    book_id int(11),
    student_id int(11),
    start_time timestamp null,
    end_time timestamp null,
    constraint fk_borrow_borrow_info_book_id foreign key (book_id) references book(id),
    constraint fk_borrow_borrow_info_student_id foreign key (student_id) references student(id)

    );

select 1 from book;

INSERT INTO borrow_info VALUES (1, 1, 1, '2018-11-07 18:50:43', '2018-12-07 18:51:01');
INSERT INTO borrow_info VALUES (2, 7, 1, '2019-07-10 10:21:00', '2019-09-10 10:21:00');
INSERT INTO borrow_info VALUES (3, 8, 1, '2019-09-10 10:21:00', '2019-10-10 10:21:00');
INSERT INTO borrow_info VALUES (4, 2, 2, '2019-03-02 16:37:00', '2019-04-02 16:37:00');
INSERT INTO borrow_info VALUES (5, 4, 2, '2019-03-12 14:25:00', '2019-04-12 14:25:00');
INSERT INTO borrow_info VALUES (6, 10, 2, '2019-07-13 16:21:00', '2019-10-13 16:21:00');
INSERT INTO borrow_info VALUES (7, 11, 2, '2019-06-09 09:40:00', '2019-07-09 09:40:00');
INSERT INTO borrow_info VALUES (8, 13, 2, '2019-01-03 15:11:00', '2019-04-03 15:11:00');
INSERT INTO borrow_info VALUES (9, 7, 3, '2019-05-15 13:13:00', '2019-06-15 13:13:00');
INSERT INTO borrow_info VALUES (10, 8, 3, '2019-04-27 13:53:00', '2019-05-27 13:53:00');
INSERT INTO borrow_info VALUES (11, 9, 3, '2019-06-01 11:32:00', '2019-07-01 11:32:00');
INSERT INTO borrow_info VALUES (12, 3, 4, '2019-07-01 09:40:00', '2019-08-01 09:40:00');
INSERT INTO borrow_info VALUES (13, 4, 4, '2019-06-19 11:40:00', '2019-07-19 11:40:00');
INSERT INTO borrow_info VALUES (14, 5, 4, '2019-06-25 09:23:00', '2019-09-25 09:23:00');
INSERT INTO borrow_info VALUES (15, 10, 4, '2019-08-27 15:30:00', '2019-09-27 15:30:00');
INSERT INTO borrow_info VALUES (16, 5, 5, '2019-01-23 14:20:00', '2019-04-23 14:20:00');
INSERT INTO borrow_info VALUES (17, 6, 5, '2019-03-09 10:45:00', '2019-04-09 10:45:00');
INSERT INTO borrow_info VALUES (18, 10, 5, '2019-06-17 11:32:00', '2019-09-17 11:32:00');

select * from borrow_info;
