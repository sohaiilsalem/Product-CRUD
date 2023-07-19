package com.example.irrigation.db.interfaces;

import com.example.irrigation.db.entities.Plot;
import com.example.irrigation.db.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findById(Long id);
    Optional<Sensor> findByPlotId(Long plotId);
    List<Sensor> findBySensorType(String sensorType);

    List<Sensor> findBySensorTypeAndTimestampBetween(String sensorType, Timestamp startTimestamp, Timestamp endTimestamp);

}
