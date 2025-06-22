CREATE TABLE expense (
  id UUID NOT NULL,
   details VARCHAR(255),
   paid_value DECIMAL(10, 2),
   payment_type VARCHAR(255),
   bill_id UUID,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   payment_date TIMESTAMP WITHOUT TIME ZONE,
   marbleshop_id UUID,
   CONSTRAINT pk_expense PRIMARY KEY (id)
);

ALTER TABLE expense ADD CONSTRAINT FK_EXPENSE_ON_BILL FOREIGN KEY (bill_id) REFERENCES bill (id);

ALTER TABLE expense ADD CONSTRAINT FK_EXPENSE_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);