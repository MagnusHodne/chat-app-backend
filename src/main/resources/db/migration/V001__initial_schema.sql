create table users (
    user_id serial primary key,
    sub varchar(255) not null,
    username varchar(255) not null,
    picture varchar(255),
    description varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table server (
    server_id serial primary key,
    owner_id int not null references users(user_id),
    name varchar(255) not null,
    description varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table channel (
    channel_id serial primary key,
    server_id int not null references server(server_id),
    name varchar(255) not null,
    description varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table message (
    message_id serial primary key,
    channel_id int not null references channel(channel_id),
    user_id int not null references users(user_id),
    content varchar(255) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

create table server_user (
    server_id int not null references server(server_id),
    user_id int not null references users(user_id),
    primary key (server_id, user_id),
    created_at timestamp not null default now(),
);