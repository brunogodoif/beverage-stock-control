CREATE TABLE beverage
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    volume DOUBLE NOT NULL,
    entry_date       TIMESTAMP    NOT NULL,
    beverage_type_id BIGINT       NOT NULL,
    section_id       BIGINT       NOT NULL,
    FOREIGN KEY (beverage_type_id) REFERENCES beverage_type (id),
    FOREIGN KEY (section_id) REFERENCES section (id)
);
