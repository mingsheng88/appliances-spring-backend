CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "appliances" (

    "id" uuid PRIMARY KEY DEFAULT  uuid_generate_v4(),
    "serialNo" character varying,
    "brand" character varying,
    "model" character varying,
    "status" character varying,
    "purchaseDate" date

);

CREATE UNIQUE INDEX idx_serialNo_brand_model ON appliances("serialNo", "brand", "model");