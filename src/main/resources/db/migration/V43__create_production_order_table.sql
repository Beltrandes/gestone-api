CREATE TABLE production_order (
  id UUID NOT NULL,
   order_id UUID,
   expected_start_date date,
   real_start_date date,
   expected_end_date date,
   real_end_date date,
   production_status VARCHAR(50),
   production_priority VARCHAR(50),
   project_url VARCHAR(255),
   notes VARCHAR(255),
   CONSTRAINT pk_production_order PRIMARY KEY (id)
);

ALTER TABLE production_order ADD CONSTRAINT FK_PRODUCTION_ORDER_ON_ORDER FOREIGN KEY (order_id) REFERENCES marbleshop_order (id);