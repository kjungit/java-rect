DROP DATABASE IF EXISTS java_basic;

CREATE DATABASE java_basic
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;


USE java_basic;



CREATE TABLE price_plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    price_plan ENUM('LITE', 'BASIC', 'PREMIUM') NOT NULL,
    capacity INT NOT NULL
);



CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    grade ENUM('NORMAL', 'VIP') NOT NULL,
    price_plan_id INT NOT NULL,

    CONSTRAINT fk_member_price_plan
        FOREIGN KEY (price_plan_id)
            REFERENCES price_plan(id)
);



INSERT INTO price_plan(price_plan, capacity)
VALUES
    ('LITE', 10),
    ('BASIC', 20),
    ('PREMIUM', 30);


SHOW TABLES;


DESC member;

DESC price_plan;



SELECT * FROM member;

SELECT * FROM price_plan;