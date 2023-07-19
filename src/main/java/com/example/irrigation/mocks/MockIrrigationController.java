package com.example.irrigation.mocks;

import com.example.irrigation.exceptions.SensorNotAvailable;
import com.example.irrigation.dtos.IrrigationResponse;
import com.example.irrigation.service.IrrigationService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MockIrrigationController {

    @Autowired
    IrrigationService irrigationService;

     public IrrigationResponse run(Long plotId, Double sensorValue) throws SensorNotAvailable, InterruptedException {
         return irrigationService.irrigate(plotId,sensorValue);
     }

}
