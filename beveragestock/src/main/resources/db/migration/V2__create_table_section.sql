CREATE TABLE section
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    total_capacity DOUBLE NOT NULL,
    used_capacity DOUBLE NOT NULL
);
