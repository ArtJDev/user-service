CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    username     varchar UNIQUE        NOT NULL,
    first_name   varchar,
    last_name    varchar,
    nick_name    varchar,
    birth_date   varchar,
    country      varchar,
    city         varchar,
    phone_number varchar,
    email        varchar,
    git_hub_link varchar,
    telegram     varchar,
    bio          varchar(2000),
    roles        varchar
);
CREATE TABLE roles
(
    id    BIGSERIAL PRIMARY KEY NOT NULL,
    roles varchar
);
CREATE TABLE user_stack
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    username  varchar,
    specialty varchar,
    stack     varchar
);
CREATE TABLE stack
(
    id    BIGSERIAL PRIMARY KEY NOT NULL,
    stack varchar
)
