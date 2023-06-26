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
    bio          text,
    roles        varchar
);

CREATE TABLE user_stack
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    username  varchar,
    specialty varchar,
    stack     varchar,
    approved  boolean
);

CREATE TABLE roles
(
    user_id bigint,
    roles   varchar
)