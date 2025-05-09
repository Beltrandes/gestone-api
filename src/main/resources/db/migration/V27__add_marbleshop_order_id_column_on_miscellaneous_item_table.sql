ALTER TABLE miscellaneous_item ADD COLUMN marbleshop_order_id UUID;

ALTER TABLE miscellaneous_item ADD CONSTRAINT FK_MISCELLANEOUS_ITEM_ON_MARBLESHOP_ORDER FOREIGN KEY (marbleshop_order_id) REFERENCES marbleshop_order (id);