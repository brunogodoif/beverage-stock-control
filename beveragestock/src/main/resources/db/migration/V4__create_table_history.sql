CREATE TABLE history
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation_type VARCHAR(50)  NOT NULL,
    volume DOUBLE NOT NULL,
    date           TIMESTAMP    NOT NULL,
    responsible    VARCHAR(100) NOT NULL,
    section_id     BIGINT       NOT NULL,
    beverage_id    BIGINT       NOT NULL,
    FOREIGN KEY (section_id) REFERENCES section (id),
    FOREIGN KEY (beverage_id) REFERENCES beverage (id)
);
