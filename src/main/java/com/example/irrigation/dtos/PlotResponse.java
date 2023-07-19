package com.example.irrigation.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PlotResponse {
    String success;
    String error;

    public PlotResponse(String success, String error){
        this.success=success;
        this.error=error;
    }
}
