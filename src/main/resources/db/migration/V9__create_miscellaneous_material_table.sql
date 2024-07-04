CREATE TABLE miscellaneous_material (
  id UUID NOT NULL,
   name VARCHAR(100) NOT NULL,
   details VARCHAR(255),
   price DECIMAL(10, 2),
   last_price DECIMAL(10, 2),
   miscellaneous_material_type VARCHAR(30),
   marbleshop_id UUID,
   CONSTRAINT pk_miscellaneous_material PRIMARY KEY (id)
);

ALTER TABLE miscellaneous_material ADD CONSTRAINT FK_MISCELLANEOUS_MATERIAL_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);