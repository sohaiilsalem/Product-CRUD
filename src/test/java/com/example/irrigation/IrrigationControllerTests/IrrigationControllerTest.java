package com.example.irrigation.IrrigationControllerTests;

import com.example.irrigation.db.entities.Plot;
import com.example.irrigation.db.entities.Sensor;
import com.example.irrigation.db.entities.Valve;
import com.example.irrigation.db.interfaces.PlotRepository;
import com.example.irrigation.db.interfaces.SensorRepository;
import com.example.irrigation.db.interfaces.ValveRepository;
import com.example.irrigation.exceptions.SensorNotAvailable;
import com.example.irrigation.mocks.MockIrrigationController;
import com.example.irrigation.mocks.SensorDevice;
import com.example.irrigation.dtos.IrrigationResponse;
import com.example.irrigation.service.ValveService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@EnableScheduling
public class IrrigationControllerTest {
    @Autowired
    private ValveRepository valveRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorDevice SensorService;

    @Autowired
    private ValveService valveService;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    MockIrrigationController mockIrrigationController;



    @Test
    public void testRunSuccess() throws SensorNotAvailable, InterruptedException, JSONException {
        // create a test plot and valve
        Plot plot = new Plot(1L, "test_plot", "corn", 500.0,false,1.0,10L,1L);
        Valve valve = new Valve(1L,"Test Valve","TEST LOC",plot,false);
        plotRepository.save(plot);
        valveRepository.save(valve);
        plot.setValveId(valve.getId());
        plotRepository.save(plot);


        // set initial moisture level
        Sensor sensor = new Sensor(1L,"MOISTURE", 0.5, new Timestamp(new Date().getTime()), 1L);
        sensorRepository.save(sensor);
        // run the controller
        IrrigationResponse response = mockIrrigationController.run(sensor.getId(),sensor.getSensorValue());

        // check that the valve was opened
        valve = valveRepository.findById(valve.getId()).get();
        plot = plotRepository.findById(plot.getId()).get();
        assertEquals(true,plot.isNeedsIrrigation());
        assertEquals("true", response.getSuccess());
        assertEquals("none",response.getError());
    }
    @Test
    public void testRunMoreThanThresh() throws SensorNotAvailable, InterruptedException, JSONException {
        // create a test plot and valve
        Plot plot = new Plot(1L, "test_plot", "corn", 500.0,false,1.0,10L,1L);
        Valve valve = new Valve(1L, "Test Valve","TEST LOC",plot,false);
        plotRepository.save(plot);
        valveRepository.save(valve);
        plot.setValveId(valve.getId());
        plotRepository.save(plot);


        // set initial moisture level
        Sensor sensor = new Sensor(1L,"MOISTURE", 2.0, new Timestamp(new Date().getTime()), 1L);
        sensorRepository.save(sensor);
        // run the controller
        IrrigationResponse response = mockIrrigationController.run(sensor.getId(),sensor.getSensorValue());

        // check that the valve was opened
        valve = valveRepository.findById(valve.getId()).get();
        assertEquals("false", response.getSuccess());
        assertEquals("none",response.getError());

    }
    @Test
    public void testRunNoSensor() throws SensorNotAvailable, InterruptedException, JSONException {
        // create a test plot and valve
        Plot plot = new Plot(1L, "test_plot", "corn", 500.0,false,1.0,10L,1L);
        Valve valve = new Valve(1L, "Test Valve","TEST LOC",plot,false);
        plotRepository.save(plot);
        valveRepository.save(valve);
        plot.setValveId(valve.getId());
        plotRepository.save(plot);
        Sensor sensor = new Sensor(4L,"MOISTURE", 0.5, new Timestamp(new Date().getTime()), 1L);
        // run the controller
        IrrigationResponse response = mockIrrigationController.run(sensor.getId(),sensor.getSensorValue());

        // check that the valve was opened
        valve = valveRepository.findById(valve.getId()).get();
        assertEquals("false", response.getSuccess());
        assertEquals("couldn't find the sensor",response.getError());
    }
    @Test
    public void testRunSuccessIrrigateScheduled() throws SensorNotAvailable, InterruptedException, JSONException {
        // create a test plot and valve
        Plot plot = new Plot(1L, "test_plot", "corn", 500.0,false,1.0,10L,1L);
        Valve valve = new Valve(1L, "Test Valve","TEST LOC",plot,false);
        plotRepository.save(plot);
        valveRepository.save(valve);
        plot.setValveId(valve.getId());
        plotRepository.save(plot);


        // set initial moisture level
        Sensor sensor = new Sensor(1L,"MOISTURE", 0.5, new Timestamp(new Date().getTime()), 1L);
        sensorRepository.save(sensor);
        // run the controller
        IrrigationResponse response = mockIrrigationController.run(sensor.getId(),sensor.getSensorValue());

        // check that the valve was opened
        valve = valveRepository.findById(valve.getId()).get();
        plot = plotRepository.findById(plot.getId()).get();
        assertEquals(true,plot.isNeedsIrrigation());
        assertEquals("true", response.getSuccess());
        assertEquals("none",response.getError());
        Thread.sleep(60000);
        plot = plotRepository.findById(plot.getId()).get();
        assertEquals(false,plot.isNeedsIrrigation());
    }


}