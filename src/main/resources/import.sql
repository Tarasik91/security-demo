insert into role(role) values('ADMIN');
insert into role(role) values('USER');

insert into hive_user(username, password, email, first_name, last_name, is_enabled) values('admin', '$2a$12$2xtiF4xTiB3YfBUD2VtiS.6vaaLnFrcFbtds58Kf1tfe4dutZUGQO', 'blablabla@ukr.net', 'first_name', 'last_name', true);

insert into user_role (user_id, role_id) values(1, 1);