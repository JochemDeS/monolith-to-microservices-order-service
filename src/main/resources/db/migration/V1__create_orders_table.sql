CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGSERIAL NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);