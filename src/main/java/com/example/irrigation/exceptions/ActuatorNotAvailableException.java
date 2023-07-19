package com.example.irrigation.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActuatorNotAvailableException extends Throwable {
private String error;
}
