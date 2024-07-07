DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS clinics CASCADE;
DROP TABLE IF EXISTS clinic_branches CASCADE;
DROP TABLE IF EXISTS clinic_cabinets CASCADE;

CREATE TABLE IF NOT EXISTS roles (
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS clinics (
    uuid uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    code varchar UNIQUE NOT NULL,
    name varchar NOT NULL,
    short_name varchar NOT NULL,
    type varchar UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS clinic_branches (
    uuid uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    code varchar UNIQUE NOT NULL,
    clinic_uuid uuid references clinics (uuid),
    is_main bool NOT NULL,
    name varchar NOT NULL,
    short_name varchar NOT NULL,
    full_address varchar NOT NULL,
    address varchar NOT NULL,
    latitude numeric(10, 7) NOT NULL,
    longitude numeric(10, 7) NOT NULL,
    is_storeyed bool NOT NULL,
    floor int
);

CREATE TABLE IF NOT EXISTS clinic_cabinets (
    uuid uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    branch_uuid uuid references clinic_branches (uuid),
    number bigint NOT NULL,
    name varchar NOT NULL,
    floor int
);

