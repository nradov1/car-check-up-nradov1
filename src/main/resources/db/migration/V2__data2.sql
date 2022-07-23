CREATE TABLE IF NOT EXISTS checkups(
    id bigserial primary key,
    workerName text,
    price int,
    performedAt text,
    carID int,
    foreign key (carID) references cars(id)
);