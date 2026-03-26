CREATE TABLE installation_order (
    id UUID NOT NULL PRIMARY KEY,
    order_id UUID NOT NULL,
    marbleshop_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    installers VARCHAR(255),
    address VARCHAR(255),
    notes TEXT,
    scheduled_date TIMESTAMP,
    completion_date TIMESTAMP,
    created_at TIMESTAMP,
    CONSTRAINT fk_inst_order FOREIGN KEY (order_id) REFERENCES marbleshop_order(id),
    CONSTRAINT fk_inst_marbleshop FOREIGN KEY (marbleshop_id) REFERENCES marbleshop(id)
);

CREATE TABLE installation_order_images (
    installation_order_id UUID NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    CONSTRAINT fk_inst_images FOREIGN KEY (installation_order_id) REFERENCES installation_order(id)
);
