insert into role (name)
values ('User');
insert into role (name)
values ('Administrator');

insert into rules (name)
values ('Read');
insert into rules (name)
values ('Create');
insert into rules (name)
values ('Delete');

insert into role_rules (role_id, rule_id)
values (1, 1);
insert into role_rules (role_id, rule_id)
values (1, 2);
insert into role_rules (role_id, rule_id)
values (2, 1);
insert into role_rules (role_id, rule_id)
values (2, 2);
insert into role_rules (role_id, rule_id)
values (2, 3);

insert into users (name, birthday, role_id)
values ('Ivan', '2020.10.10', 2);

insert into state (name)
values ('New');
insert into state (name)
values ('Accepted');
insert into state (name)
values ('Done');

insert into category (name)
values ('Help');

insert into item (user_id, category_id, state_id)
values (1, 1, 2);

insert into comments (item_id, text)
values (1, 'Hello');
insert into comments (item_id, text)
values (1, 'I have a problem');

insert into attachs (item_id, file_path)
values (1, 'scr/screenshot.png');
