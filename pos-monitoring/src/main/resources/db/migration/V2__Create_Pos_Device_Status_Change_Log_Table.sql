CREATE TABLE pos_device_status_change_log (
    id SERIAL NOT NULL PRIMARY KEY,
    pos_device_id INT NOT NULL,
    online BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (pos_device_id) REFERENCES pos_device(id)
);
