CREATE TABLE quote_item (
  id UUID NOT NULL,
   name VARCHAR(255),
   details VARCHAR(255),
   measurex DECIMAL(10, 3),
   measurey DECIMAL(10, 3),
   quantity INTEGER,
   material_id UUID,
   value DECIMAL(10,2),
   area DECIMAL(10, 3),
   total_value DECIMAL(10, 2),
   total_area DECIMAL(10, 3),
   quotation_id UUID,
   CONSTRAINT pk_quote_item PRIMARY KEY (id)
);

ALTER TABLE quote_item ADD CONSTRAINT FK_QUOTE_ITEM_ON_MATERIAL FOREIGN KEY (material_id) REFERENCES material (id);

ALTER TABLE quote_item ADD CONSTRAINT FK_QUOTE_ITEM_ON_QUOTATION FOREIGN KEY (quotation_id) REFERENCES quotation (id);