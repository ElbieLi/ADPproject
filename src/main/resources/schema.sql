-- ----------------------------------------------
-- DDL Statements for tables
-- ----------------------------------------------

CREATE TABLE "CUSTOMERS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "CUSTOMER_NAME" VARCHAR(255), "PASSWORD" VARCHAR(255), EMAIL VARCHAR(255));
