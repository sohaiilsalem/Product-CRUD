package com.example.irrigation.controller;

import com.example.irrigation.exceptions.ActuatorNotAvailableException;
import com.example.irrigation.exceptions.SensorNotAvailable;
import com.example.irrigation.dtos.IrrigationResponse;
import com.example.irrigation.service.IrrigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/irrigate")
public class IrrigationController {
    @Autowired
    IrrigationService irrigationService;

    @PostMapping("/{id}")
    public IrrigationResponse irrigate(@PathVariable Long id, @RequestBody Double value) throws ActuatorNotAvailableException, SensorNotAvailable, InterruptedException {
        return irrigationService.irrigate(id,value);
    }
}
