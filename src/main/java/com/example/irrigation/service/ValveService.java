package com.example.irrigation.service;

import com.example.irrigation.db.entities.Valve;
import com.example.irrigation.db.interfaces.ValveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValveService {
@Autowired
    ValveRepository valveRepository;
    public void openValve(Long valveId) {
        // set the valve isOpen flag to true
        Valve valve = valveRepository.findById(valveId).orElse(null);
        if (valve != null) {
            valve.setIsOpen(true);
            valveRepository.save(valve);
        }
    }

    public void closeValve(Long valveId) {
        // set the valve isOpen flag to false
        Valve valve = valveRepository.findById(valveId).orElse(null);
        if (valve != null) {
            valve.setIsOpen(false);
            valveRepository.save(valve);
        }
    }

}
