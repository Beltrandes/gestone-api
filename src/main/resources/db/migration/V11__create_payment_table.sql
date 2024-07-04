CREATE TABLE payment (
  id UUID NOT NULL,
   details VARCHAR(255),
   total_value DECIMAL(10, 2),
   payed_value DECIMAL(10, 2),
   balance_value DECIMAL(10, 2),
   marbleshop_order_id UUID,
   payment_type VARCHAR(30),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_payment PRIMARY KEY (id)
);

ALTER TABLE payment ADD CONSTRAINT FK_PAYMENT_ON_MARBLESHOP_ORDER FOREIGN KEY (marbleshop_order_id) REFERENCES marbleshop_order (id);