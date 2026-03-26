CREATE TABLE visit (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    marbleshop_id UUID NOT NULL,
    reason VARCHAR(50) NOT NULL,
    scheduled_at TIMESTAMP NOT NULL,
    notes VARCHAR(1000),
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    completed_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
    CONSTRAINT fk_marbleshop FOREIGN KEY (marbleshop_id) REFERENCES marbleshop(id) ON DELETE CASCADE
);
