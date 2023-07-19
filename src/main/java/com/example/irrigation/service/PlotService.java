package com.example.irrigation.service;

import com.example.irrigation.db.interfaces.PlotRepository;
import com.example.irrigation.db.entities.Plot;
import com.example.irrigation.dtos.PlotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotService {
    @Autowired
    PlotRepository plotRepository;
    public PlotResponse addPlot(Plot plot){
        Plot newPlot = plotRepository.save(plot);
        if(newPlot!=null){
            return new PlotResponse("true","false");
        }
        return new PlotResponse("false","true");
    }
    public PlotResponse editPlot(Plot editedPlot) {
        Optional<Plot> plot = plotRepository.findById(editedPlot.getId());
        if (plot.isPresent()){
            plotRepository.save(editedPlot);
            return new PlotResponse("true","false");
        }
        return new PlotResponse("false","false");
    }
    public List<Plot> getAllPlots(){
        return plotRepository.findAll();
    }
}
