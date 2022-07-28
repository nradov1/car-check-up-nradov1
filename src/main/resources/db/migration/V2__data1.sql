CREATE TABLE IF NOT EXISTS car(
    id bigserial primary key,
    vin text,
    production_year int,
    created_on timestamp,
    car_model_id uuid constraint carmodels_fk references car_models(id)
);