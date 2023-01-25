create table if not exists test_db.order_products (
    order_id int constraint fk_order_id references test_db."order" on update cascade on delete cascade,
    products_id int constraint fk_product_id references test_db.product on update cascade on delete cascade
);