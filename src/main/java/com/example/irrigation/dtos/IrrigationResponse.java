package com.example.irrigation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IrrigationResponse {
    String success;
    String error;
}
