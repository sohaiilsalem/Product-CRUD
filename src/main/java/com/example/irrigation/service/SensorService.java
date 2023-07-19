package com.example.irrigation.service;

import com.example.irrigation.db.entities.Sensor;
import com.example.irrigation.db.interfaces.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SensorService {
    @Autowired
    private SensorRepository SensorRepository;


    public Sensor saveSensor(Sensor Sensor) {
        return SensorRepository.save(Sensor);
    }


    public List<Sensor> getSensorByType(String sensorType) {
        return SensorRepository.findBySensorType(sensorType);
    }

    public List<Sensor> getSensorByTypeAndTimestampBetween(String sensorType, Timestamp startTimestamp, Timestamp endTimestamp) {
        return SensorRepository.findBySensorTypeAndTimestampBetween(sensorType, startTimestamp, endTimestamp);
    }
}

