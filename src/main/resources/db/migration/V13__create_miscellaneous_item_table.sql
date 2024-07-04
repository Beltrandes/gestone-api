CREATE TABLE miscellaneous_item (
  id UUID NOT NULL,
   name VARCHAR(100),
   details VARCHAR(255),
   quantity INTEGER,
   unit_value DECIMAL(10, 2),
   total_value DECIMAL(10, 2),
   miscellaneous_material_id UUID,
   quotation_id UUID,
   marbleshop_order_id UUID,
   CONSTRAINT pk_miscellaneous_item PRIMARY KEY (id)
);

ALTER TABLE miscellaneous_item ADD CONSTRAINT FK_MISCELLANEOUS_ITEM_ON_MARBLESHOP_ORDER FOREIGN KEY (marbleshop_order_id) REFERENCES marbleshop_order (id);

ALTER TABLE miscellaneous_item ADD CONSTRAINT FK_MISCELLANEOUS_ITEM_ON_MISCELLANEOUS_MATERIAL FOREIGN KEY (miscellaneous_material_id) REFERENCES miscellaneous_material (id);

ALTER TABLE miscellaneous_item ADD CONSTRAINT FK_MISCELLANEOUS_ITEM_ON_QUOTATION FOREIGN KEY (quotation_id) REFERENCES quotation (id);