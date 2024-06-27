CREATE TABLE approved_employee (
  id SERIAL,
   employee_id UUID,
   email VARCHAR(100),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   registered BOOLEAN,
   registration_date TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_approved_employee PRIMARY KEY (id)
);

ALTER TABLE approved_employee ADD CONSTRAINT FK_APPROVED_EMPLOYEE_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES employee (id);