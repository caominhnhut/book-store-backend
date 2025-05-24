-- Create users table
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(15)  NOT NULL UNIQUE,
    email        VARCHAR(100),
    full_name    VARCHAR(50),
    password     VARCHAR(100) NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
