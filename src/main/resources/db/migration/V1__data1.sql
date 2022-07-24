CREATE TABLE IF NOT EXISTS car(
    id bigserial primary key,
    vin text,
    manufacturer text,
    model text,
    production_year int,
    created_on text
);