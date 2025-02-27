CREATE TYPE rack_status_enum AS ENUM ('ACTIVE', 'RETURNED', 'REPAIR', 'OUTDATED', 'BRICKED');

DROP TABLE IF EXISTS T_BOOKING;
DROP TABLE IF EXISTS R_RACK_ASSET;
DROP TABLE IF EXISTS T_RACK;
DROP TABLE IF EXISTS T_TEAM_MEMBER;
DROP TABLE IF EXISTS T_TEAM;

create table T_TEAM (
                        id bigint primary key ,
                        name varchar(50),
                        product varchar (50),
                        created_at timestamp default now(),
                        modified_at timestamp,
                        default_location varchar (50)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;

create table T_TEAM_MEMBER (
                               id bigint primary key,
                               team_id bigint references T_TEAM(id),
                               ctw_id varchar(50) not null unique,
                               name varchar(50),
                               created_at timestamp default now(),
                               modified_at timestamp
);

CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_MEMBER_ID;

create table T_RACK (
                        id bigint primary key ,
                        serial_number varchar(20) not null,
                        team_id bigint references T_TEAM(id),
                        status varchar(20) check ( status in  ('ACTIVE', 'RETURNED', 'REPAIR', 'OUTDATED', 'BRICKED') ) ,
                        created_at timestamp default now(),
                        modified_at timestamp,
                        default_location varchar (50)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;

create table R_RACK_ASSET(
                             id        bigint primary key ,
                             asset_tag varchar(10) not null,
                             rack_id   bigint references t_rack(id)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ASSET_ID;

create table T_BOOKING (
                           id bigint primary key,
                           rack_id bigint references t_rack(id),
                           requester_id bigint references T_TEAM_MEMBER(id),
                           book_from timestamp,
                           book_to timestamp,
                           created_at timestamp default now(),
                           modified_at timestamp
);

CREATE SEQUENCE IF NOT EXISTS SEQ_BOOKING_ID;
