CREATE TABLE cars IF NOT EXISTS(
    id bigserial primary key,
    manufacturer text,
    model text,
    productionyear int,
    createdOn text
);
CREATE TABLE checkups IF NOT EXISTS(
    id bigserial,
    workerName text,
    price int,
    performedAt text,
    carID int,
    foreign key carID references cars(id)
);