CREATE TABLE marbleshop_sub_item (
  id UUID NOT NULL,
   name VARCHAR(40) NOT NULL,
   description VARCHAR(250),
   measurex DECIMAL(10, 2) NOT NULL,
   measurey DECIMAL(10, 2) NOT NULL,
   quantity INTEGER,
   value DECIMAL(10, 2),
   area DECIMAL(10, 2),
   marbleshop_material_id UUID,
   marbleshop_sub_item_type VARCHAR(255),
   marbleshop_item_id UUID,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_marbleshop_sub_item PRIMARY KEY (id)
);

ALTER TABLE marbleshop_sub_item ADD CONSTRAINT FK_MARBLESHOP_SUB_ITEM_ON_MARBLESHOP_ITEM FOREIGN KEY (marbleshop_item_id) REFERENCES marbleshop_item (id);

ALTER TABLE marbleshop_sub_item ADD CONSTRAINT FK_MARBLESHOP_SUB_ITEM_ON_MARBLESHOP_MATERIAL FOREIGN KEY (marbleshop_material_id) REFERENCES marbleshop_material (id);