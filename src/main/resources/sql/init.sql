--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9
-- Dumped by pg_dump version 12.9

-- Started on 2022-02-02 13:24:10 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA enib;



SET default_table_access_method = heap;


CREATE TABLE enib.soldes (
    id character(8) NOT NULL,
    montant DECIMAL(15,2) NOT NULL,
    devise character(3) NOT NULL
);
CREATE UNIQUE INDEX soldes_pk ON enib.soldes USING btree (id);


CREATE TABLE enib.devises (
    code character(3) NOT NULL,
    libelle character(32) NOT NULL
);

CREATE UNIQUE INDEX devises_pk ON enib.devises USING btree (code);

