CREATE TABLE slab_movement (
  id UUID NOT NULL,
   slab_id UUID,
   area_moved DECIMAL(10, 2),
   production_order_item_id UUID,
   movement_type VARCHAR(255),
   CONSTRAINT pk_slab_movement PRIMARY KEY (id)
);

ALTER TABLE slab_movement ADD CONSTRAINT FK_SLAB_MOVEMENT_ON_PRODUCTION_ORDER_ITEM FOREIGN KEY (production_order_item_id) REFERENCES production_order_item (id);

ALTER TABLE slab_movement ADD CONSTRAINT FK_SLAB_MOVEMENT_ON_SLAB FOREIGN KEY (slab_id) REFERENCES slab (id);