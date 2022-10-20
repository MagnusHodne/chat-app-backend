CREATE TABLE users (
    user_id serial primary key,
    sub varchar(255) not null,
    username varchar(255) not null,
    picture varchar(255),
    description varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    last_login timestamp not null default now()
);

CREATE TABLE servers (
    server_id serial primary key,
    owner_id int REFERENCES users(user_id),
    name varchar(255) not null,
    description varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

CREATE TABLE channels (
    channel_id serial primary key,
    server_id int not null REFERENCES servers(server_id),
    name varchar(255) not null,
    description varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

CREATE TABLE messages (
    message_id serial primary key,
    channel_id int not null REFERENCES channels(channel_id),
    user_id int not null REFERENCES users(user_id),
    content varchar(255) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

CREATE TABLE server_user (
    server_id int not null REFERENCES servers(server_id),
    user_id int not null REFERENCES users(user_id),
    primary key (server_id, user_id),
    created_at timestamp not null default now()
);