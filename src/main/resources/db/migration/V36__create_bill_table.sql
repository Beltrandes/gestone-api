CREATE TABLE bill (
   id UUID NOT NULL,
   description VARCHAR(255),
   total_value DECIMAL(10, 2),
   paid_value DECIMAL(10, 2),
   due_date TIMESTAMP WITHOUT TIME ZONE,
   payment_date TIMESTAMP WITHOUT TIME ZONE,
   status VARCHAR(255),
   category VARCHAR(255),
   notes TEXT,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   marbleshop_id UUID,
   CONSTRAINT pk_bill PRIMARY KEY (id)
);
ALTER TABLE bill ADD CONSTRAINT FK_BILL_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);