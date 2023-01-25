create table if not exists test_db.order (
    id serial not null primary key,
    "date" date not null,
    "cost" decimal default 0
);