CREATE TABLE "user" (
    id UUID NOT NULL,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(50) NOT NULL,
    phone VARCHAR(50),
    user_type SMALLINT,
    marbleshop_id UUID NOT NULL,
    created_at TIMESTAMP,
    CONSTRAINT pk_user PRIMARY KEY (id)

);

 