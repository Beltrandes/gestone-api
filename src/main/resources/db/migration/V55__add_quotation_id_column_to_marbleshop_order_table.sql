ALTER TABLE marbleshop_order ADD COLUMN quotation_id UUID;
ALTER TABLE marbleshop_order ADD CONSTRAINT fk_quotation FOREIGN KEY (quotation_id) REFERENCES quotation(id);
