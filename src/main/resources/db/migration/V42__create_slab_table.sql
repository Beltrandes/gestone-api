CREATE TABLE slab (
  id UUID NOT NULL,
   material_id UUID,
   density_measure DECIMAL,
   measurex DECIMAL(10, 2),
   measurey DECIMAL(10, 2),
   area DECIMAL,
   quality VARCHAR(30),
   notes VARCHAR(255),
   status VARCHAR(50),
   entry_date TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_slab PRIMARY KEY (id)
);

ALTER TABLE slab ADD CONSTRAINT FK_SLAB_ON_MATERIAL FOREIGN KEY (material_id) REFERENCES marbleshop_material (id);