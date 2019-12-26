create table if not exists users
(
    id         serial      not null
        constraint users_pkey
            primary key,
    email      varchar(100)
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    first_name varchar(50),
    last_name  varchar(50),
    password   varchar(128) not null,
    username   varchar(50) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);

create table if not exists task_repo
(
    id           serial       not null
        constraint task_repo_pkey
            primary key,
    date_add     timestamp    not null,
    description  varchar(255) not null,
    task_name    varchar(255) not null
        constraint uk_tisr4avf3o5k3rcqwfved3vxu
            unique,
    task_status  varchar(255) not null,
    owner_id     integer      not null
        constraint fkgmd482t5450auc2byvu172r90
            references users,
    performer_id integer      not null
        constraint fkqssbx3jw0ypgb3lac74lfxjrk
            references users
);