package com.example.irrigation.mocks;

import com.example.irrigation.db.entities.Sensor;
import com.example.irrigation.exceptions.SensorNotAvailable;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SensorDevice {
    private boolean successful;
    private Random random = new Random();

    public void connect(){
        successful=true;
    }
    public void disconnect(){
        successful= false;
    }
    public void sendData(Sensor value) throws SensorNotAvailable {
        if(!successful){
            throw new SensorNotAvailable("Couldn't connect to the sensor");
        }
        double moistureLevel = random.nextDouble();
        value.setSensorValue(moistureLevel);
    }
}
