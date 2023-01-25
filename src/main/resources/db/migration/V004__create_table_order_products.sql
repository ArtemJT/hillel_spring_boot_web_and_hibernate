create table if not exists public.order_products (
    order_id int constraint fk_order_id references test_db."order",
    product_id int constraint fk_product_id references test_db.product
);