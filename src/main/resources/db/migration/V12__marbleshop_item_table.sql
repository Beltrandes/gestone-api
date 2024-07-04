CREATE TABLE marbleshop_item (
  id UUID NOT NULL,
   name VARCHAR(100) NOT NULL,
   description VARCHAR(255),
   measurex DECIMAL(10, 3),
   measurey DECIMAL(10, 3),
   quantity INTEGER,
   marbleshop_material_id UUID,
   unit_value DECIMAL(10, 2),
   unit_area DECIMAL(10, 3),
   total_value DECIMAL(10, 2),
   total_area DECIMAL(10, 3),
   marbleshop_item_type VARCHAR(30),
   quotation_id UUID,
   marbleshop_order_id UUID,
   CONSTRAINT pk_marbleshop_item PRIMARY KEY (id)
);

ALTER TABLE marbleshop_item ADD CONSTRAINT FK_MARBLESHOP_ITEM_ON_MARBLESHOP_MATERIAL FOREIGN KEY (marbleshop_material_id) REFERENCES marbleshop_material (id);

ALTER TABLE marbleshop_item ADD CONSTRAINT FK_MARBLESHOP_ITEM_ON_MARBLESHOP_ORDER FOREIGN KEY (marbleshop_order_id) REFERENCES marbleshop_order (id);

ALTER TABLE marbleshop_item ADD CONSTRAINT FK_MARBLESHOP_ITEM_ON_QUOTATION FOREIGN KEY (quotation_id) REFERENCES quotation (id);