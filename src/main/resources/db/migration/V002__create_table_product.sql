create table if not exists "order" (
    id serial not null primary key,
    date date,
    cost decimal,
    products
);