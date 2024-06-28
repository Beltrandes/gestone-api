CREATE TABLE customer (
  id UUID NOT NULL,
   name VARCHAR(100),
   phone VARCHAR(30),
   email VARCHAR(150),
   address VARCHAR(255),
   marbleshop_id UUID,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_customer PRIMARY KEY (id)
);

ALTER TABLE customer ADD CONSTRAINT FK_CUSTOMER_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);