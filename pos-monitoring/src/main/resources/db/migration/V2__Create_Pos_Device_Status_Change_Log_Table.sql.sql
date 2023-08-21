CREATE TABLE pos_device_status_change_log (
    id INT PRIMARY KEY NOT NULL,
    pos_device_id INT,
    online BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (pos_device_id) REFERENCES pos_device(id)
);
