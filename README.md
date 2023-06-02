# Stack
**FE**
><img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">
><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
><img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
><img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
><img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
><img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
**BE**
><img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
><img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
**DB**
><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
><img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">


---

<!-- <img src="https://img.shields.io/badge/기술이름-#제외색상번호?style=for-the-badge&logo=아이콘이름&logoColor=white"> -->
# Board 주요기능 
1. Write
2. Search
3. Update
4. Delete
5. Paging

# Member 주요 기능
1. Login
2. Register
3. Update
4. Delete
5. Unique E-mail

```
create table board_table
(
id             bigint auto_increment primary key,
created_time   datetime     null,
updated_time   datetime     null,
board_contents varchar(500) null,
board_hits     int          null,
board_title    varchar(255) null,
file_attached  int          null
);

create table board_file_table
(
id                 bigint auto_increment primary key,
created_time       datetime     null,
updated_time       datetime     null,
original_file_name varchar(255) null,
stored_file_name   varchar(255) null,
board_id           bigint       null,
constraint FKcfxqly70ddd02xbou0jxgh4o3
    foreign key (board_id) references board_table (id) on delete cascade
);
```
There is no necessary to create a comment table, member table.

---

## mysql DataBase 계정 생성 및 권한 부여 
```
create database [db_name];
create user [db_username] identified by '1234';
grant all privileges on [db_name].* to [db_username];
```
# Done
1. upgrade board that has a function a member.

# Todo?
1. on offline store a board in user device and when come to online sand a data to store a board that wrote before
