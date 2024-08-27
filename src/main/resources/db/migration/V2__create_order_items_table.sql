CREATE TABLE IF NOT EXISTS order_items (
      order_id BIGSERIAL NOT NULL,
      product_id BIGSERIAL NOT NULL,
      quantity INT NOT NULL DEFAULT 1,
      PRIMARY KEY (order_id, product_id),
      CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);
