CREATE TABLE marbleshop (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR (20) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE
);
ALTER TABLE marbleshop ADD CONSTRAINT pk_marbleshop PRIMARY KEY (id);
ALTER TABLE marbleshop_user ADD CONSTRAINT fk_marbleshop FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);