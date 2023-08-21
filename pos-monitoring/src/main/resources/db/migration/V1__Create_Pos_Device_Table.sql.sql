CREATE TABLE pos_device (
    id INT PRIMARY KEY,
    device_id VARCHAR(255),
    location VARCHAR(255),
    online BOOLEAN,
    provider VARCHAR(255),
    last_activity TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
