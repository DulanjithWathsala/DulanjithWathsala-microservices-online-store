CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_number VARCHAR(255) DEFAULT NULL,
    sku_code VARCHAR(255),
    price DECIMAL(19, 2),
    quantity INT
);
