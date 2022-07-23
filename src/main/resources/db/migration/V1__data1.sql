CREATE TABLE IF NOT EXISTS cars(
    id bigserial primary key,
    vin text,
    manufacturer text,
    model text,
    productionyear int,
    createdOn text
);