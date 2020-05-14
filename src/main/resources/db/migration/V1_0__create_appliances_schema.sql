CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "appliance" (

    "id" uuid PRIMARY KEY DEFAULT  uuid_generate_v4(),
    "serial_no" character varying,
    "brand" character varying,
    "model" character varying,
    "status" character varying,
    "purchaseDate" date

);

CREATE UNIQUE INDEX idx_serialNo_brand_model ON appliance(serial_no, brand, model);