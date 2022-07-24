CREATE TABLE IF NOT EXISTS check_ups(
    id bigserial primary key,
    worker_name text,
    price int,
    performed_at text,
    car_id int constraint carcheckup_fk references car(id)

);