package com.example.irrigation.controller;

import com.example.irrigation.db.entities.Plot;
import com.example.irrigation.dtos.PlotResponse;
import com.example.irrigation.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/plot")
public class PlotsController {
    @Autowired
    PlotService plotService;
    @PostMapping("/add")
    public PlotResponse addPlot(@RequestBody Plot plot){
        return plotService.addPlot(plot);
    }
    @PostMapping("/edit")
    public PlotResponse editPlot(@RequestBody Plot plot){
        return plotService.editPlot(plot);
    }
    @GetMapping("/list")
    public List<Plot> listAllPlots(){
        return plotService.getAllPlots();
    }
}
