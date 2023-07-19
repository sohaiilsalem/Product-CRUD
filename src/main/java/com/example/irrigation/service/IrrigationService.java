package com.example.irrigation.service;

import com.example.irrigation.db.entities.Plot;
import com.example.irrigation.db.entities.Sensor;
import com.example.irrigation.db.interfaces.PlotRepository;
import com.example.irrigation.db.interfaces.SensorRepository;
import com.example.irrigation.exceptions.SensorNotAvailable;
import com.example.irrigation.mocks.SensorDevice;
import com.example.irrigation.dtos.IrrigationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

@Service
public class IrrigationService {
    @Autowired
    private SensorDevice sensorDevice;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private ValveService valveService;
    @Autowired
    private Map<Long, Plot> needsIrrigation;
    @Value("${sensor.retry.maxAttempts}")
    String maxAttemps;

//    @Scheduled(fixedRate = 30000)
//    public void irrigateScheduled() {
//       if(!needsIrrigation.isEmpty()) {
//           needsIrrigation.forEach((plotId, plot) -> {
//               valveService.openValve(plot.getValveId());
//               try {
//                   Thread.sleep(plot.getTimeSlot() * 1000);
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
//               valveService.closeValve(plot.getValveId());
//               plot.setNeedsIrrigation(false);
//               needsIrrigation.remove(plotId);
//           });
//       }
//    }
@Scheduled(fixedRate = 30000)
public void irrigateScheduled() {
    Iterator<Map.Entry<Long, Plot>> iterator = needsIrrigation.entrySet().iterator();
    while (iterator.hasNext()) {
        Map.Entry<Long, Plot> entry = iterator.next();
        Plot plot = entry.getValue();
        valveService.openValve(plot.getValveId());
        try {
            Thread.sleep(plot.getTimeSlot() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        valveService.closeValve(plot.getValveId());
        plot.setNeedsIrrigation(false);
        plotRepository.save(plot);
        iterator.remove();
    }
}
    public IrrigationResponse irrigate(Long id, Double value) throws SensorNotAvailable, InterruptedException {

        Optional<Sensor> sensor = sensorRepository.findById(id);
        if (sensor.isEmpty()) {
            return new IrrigationResponse("false","couldn't find the sensor");
        }
        int retryTimes=0;
        boolean successful=false;
        while(!successful && retryTimes<Integer.parseInt(maxAttemps)) {
            //Mocking sensor connection
            sensorDevice.connect();
            try {
                sensorDevice.sendData(sensor.get());
            } catch (SensorNotAvailable e) {
                retryTimes += 1;
            }
            successful = true;
            sensorDevice.disconnect();
        }
        if(!successful){
            throw new SensorNotAvailable("couldn't connect to the sensor");
        }
        Long plotId = sensor.get().getPlotId();
        Optional<Plot> plot = plotRepository.findById(plotId);
        if (plot.isEmpty()) {
        return new IrrigationResponse("false","couldn't find the plot");
        }
        Double moistureThresh = plot.get().getMoistureThresh();
        if (value <= moistureThresh) {
            plot.get().setNeedsIrrigation(true);
            plotRepository.save(plot.get());
            needsIrrigation.put(plotId,plot.get());
            return new IrrigationResponse("true", "none");
        }
    return new IrrigationResponse("false","none");
    }

}
