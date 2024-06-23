CREATE TABLE marbleshop (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR (20) NOT NULL
);
ALTER TABLE marbleshop ADD CONSTRAINT pk_marbleshop PRIMARY KEY (id);
ALTER TABLE "user" ADD CONSTRAINT fk_marbleshop FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);