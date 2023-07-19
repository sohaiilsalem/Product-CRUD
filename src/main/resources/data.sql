CREATE TABLE IF NOT EXISTS plot (
  id BIGSERIAL PRIMARY KEY,
  plot_name VARCHAR(250) NOT NULL,
  crop_type VARCHAR(250) NOT NULL,
  plot_size BIGINT,
  needs_irrigations BOOLEAN,
  moisture_thresh BIGINT,
  valve_id INT,
  time_slot BIGINT
);

CREATE TABLE IF NOT EXISTS sensor (
  id BIGSERIAL PRIMARY KEY,
  sensor_type VARCHAR(250) NOT NULL,
  sensor_value BIGINT NOT NULL,
  timestamp TIMESTAMP DEFAULT NULL,
  plot_id INT REFERENCES plot(id)
);
CREATE TABLE IF NOT EXISTS valves (
    id BIGSERIAL NOT NULL ,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    plot_id BIGINT NOT NULL,
    is_open BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (plot_id) REFERENCES plot(id)
);