ALTER TABLE material ADD COLUMN marbleshop_id UUID;
ALTER TABLE material ADD CONSTRAINT FK_MATERIAL_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);

