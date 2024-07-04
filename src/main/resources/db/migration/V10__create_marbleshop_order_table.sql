CREATE TABLE marbleshop_order (
  id UUID NOT NULL,
   name VARCHAR(100) NOT NULL,
   details VARCHAR(255),
   total_value DECIMAL,
   total_area DECIMAL,
   deadline_days INTEGER,
   status VARCHAR(30),
   customer_id UUID,
   marbleshop_id UUID,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_marbleshop_order PRIMARY KEY (id)
);

ALTER TABLE marbleshop_order ADD CONSTRAINT FK_MARBLESHOP_ORDER_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);
ALTER TABLE marbleshop_order ADD CONSTRAINT FK_MARBLESHOP_ORDER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);