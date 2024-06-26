CREATE TABLE employee (
  id UUID NOT NULL,
   name VARCHAR(255),
   document_number VARCHAR(255),
   salary DECIMAL,
   phone VARCHAR(255),
   employee_role SMALLINT,
   marbleshop_id UUID,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee ADD CONSTRAINT FK_EMPLOYEE_ON_MARBLESHOP FOREIGN KEY (marbleshop_id) REFERENCES marbleshop (id);