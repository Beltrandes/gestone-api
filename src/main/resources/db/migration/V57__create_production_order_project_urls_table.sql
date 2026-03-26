CREATE TABLE production_order_project_urls (
    production_order_id UUID NOT NULL,
    project_url VARCHAR(255),
    CONSTRAINT fk_prod_order_urls FOREIGN KEY (production_order_id) REFERENCES production_order(id)
);

-- Migrando os dados que existiam na coluna antiga para a nova tabela
INSERT INTO production_order_project_urls (production_order_id, project_url)
SELECT id, project_url FROM production_order WHERE project_url IS NOT NULL;

-- Apagando a coluna antiga que não é mais usada pelo JPA
ALTER TABLE production_order DROP COLUMN project_url;
