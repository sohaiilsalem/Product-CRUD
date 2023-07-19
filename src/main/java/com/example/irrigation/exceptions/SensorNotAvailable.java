package com.example.irrigation.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SensorNotAvailable extends Throwable {
    public String error;

}
