create table if not exists test_db.product (
    id serial not null primary key,
    "name" text not null,
    "cost" decimal
);