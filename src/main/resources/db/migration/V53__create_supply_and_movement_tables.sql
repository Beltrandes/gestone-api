CREATE TABLE supply (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    unit_of_measure VARCHAR(50) NOT NULL,
    minimum_stock INTEGER NOT NULL DEFAULT 0,
    current_stock INTEGER NOT NULL DEFAULT 0,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    marbleshop_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_marbleshop_supply FOREIGN KEY (marbleshop_id) REFERENCES marbleshop(id) ON DELETE CASCADE
);

CREATE TABLE supply_movement (
    id UUID PRIMARY KEY,
    supply_id UUID NOT NULL,
    type VARCHAR(20) NOT NULL,
    category VARCHAR(50) NOT NULL,
    quantity INTEGER NOT NULL,
    notes VARCHAR(1000),
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_supply_movement FOREIGN KEY (supply_id) REFERENCES supply(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_movement FOREIGN KEY (user_id) REFERENCES marbleshop_user(id) ON DELETE CASCADE
);
