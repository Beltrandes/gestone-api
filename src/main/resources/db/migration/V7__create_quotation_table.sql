CREATE TABLE quotation (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    details VARCHAR(255),
    address VARCHAR(255) NOT NULL,
    deadline_days INTEGER NOT NULL,
    days_for_due INTEGER NOT NULL,
    total_value DECIMAL(10, 3) NOT NULL,
    total_area DECIMAL(10, 3) NOT NULL,
    quotation_status SMALLINT NOT NULL,
    customer_id UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_quotation PRIMARY KEY (id)
);

ALTER TABLE quotation ADD CONSTRAINT FK_QUOTATION_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);
