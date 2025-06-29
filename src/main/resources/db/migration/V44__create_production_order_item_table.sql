CREATE TABLE production_order_item (
  id UUID NOT NULL,
   production_order_id UUID,
   marbleshop_item_id UUID,
   status VARCHAR(50),
   notes VARCHAR(255),
   CONSTRAINT pk_production_order_item PRIMARY KEY (id)
);

ALTER TABLE production_order_item ADD CONSTRAINT FK_PRODUCTION_ORDER_ITEM_ON_MARBLESHOP_ITEM FOREIGN KEY (marbleshop_item_id) REFERENCES marbleshop_item (id);

ALTER TABLE production_order_item ADD CONSTRAINT FK_PRODUCTION_ORDER_ITEM_ON_PRODUCTION_ORDER FOREIGN KEY (production_order_id) REFERENCES production_order (id);