DROP TABLE IF EXISTS accident CASCADE;
DROP TABLE IF EXISTS accident_type CASCADE;
DROP TABLE IF EXISTS rule CASCADE;
DROP TABLE IF EXISTS accident_rule CASCADE;

CREATE TABLE accident_type (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    accident_type_id int REFERENCES accident_type(id)
);

CREATE TABLE rule (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE accident_rule (
    accident_id int REFERENCES accident(id),
    rule_id int REFERENCES rule(id),
    PRIMARY KEY (accident_id, rule_id)
)
