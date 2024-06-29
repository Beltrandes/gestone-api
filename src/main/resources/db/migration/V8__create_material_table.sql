CREATE TABLE material (
    id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    details VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    last_price DECIMAL(10, 2),
    material_type SMALLINT,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_material PRIMARY KEY (id)
)