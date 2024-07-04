CREATE TABLE marbleshop_material (
  id UUID NOT NULL,
   name VARCHAR(100) NOT NULL,
   details VARCHAR(255),
   price DECIMAL(10, 2) NOT NULL,
   last_price DECIMAL(10, 2),
   marbleshop_material_type VARCHAR(30),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   marbleshop_id UUID,
   CONSTRAINT pk_marbleshop_material PRIMARY KEY (id)
);

ALTER TABLE marbleshop_material ADD CONSTRAINT FK_MARBLESHOP_MATERIAL_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);