package com.example.irrigation.db.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
@Entity
@Table(name="sensor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_type")
    private String sensorType;

    @Column(name = "sensor_value")
    private Double sensorValue;

    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name="plot_id")
    private Long plotId;
}
