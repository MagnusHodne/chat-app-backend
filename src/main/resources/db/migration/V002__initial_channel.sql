insert into users(sub, username, picture, description) values('auth|invalid', 'test@user.com', '', '');
insert into servers(owner_id, name, description) values(1, 'test', 'test server');
insert into channels(server_id, name, description) values(1, 'test', 'test channel');