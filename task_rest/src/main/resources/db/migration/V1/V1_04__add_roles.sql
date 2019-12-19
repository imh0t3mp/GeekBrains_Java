create table if not exists roles
(
    name varchar(50) not null
        constraint roles_pkey
            primary key
);

create table if not exists user_roles
(
    user_id   integer     not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_name varchar(50) not null
        constraint fkdcdh0gl1mdce42vy0klyio6fi
            references roles,
    constraint user_roles_pkey
        primary key (user_id, role_name)
);

TRUNCATE roles CASCADE ;

INSERT INTO roles
VALUES ('ADMIN_ROLE'),
       ('ROOT_ROLE'),
       ('USER_ROLE');

TRUNCATE user_roles CASCADE;

INSERT INTO user_roles
VALUES (1, 'ADMIN_ROLE'),
       (1, 'ROOT_ROLE'),
       (2, 'USER_ROLE');
