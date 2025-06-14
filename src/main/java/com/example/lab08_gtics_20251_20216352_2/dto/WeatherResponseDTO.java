package com.example.lab08_gtics_20251_20216352_2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WeatherResponseDTO {
    private double temp_c;
    private String condition;
    private double feelslike_c;
    private int humidity;

    public WeatherResponseDTO() {}

    public WeatherResponseDTO(double temp_c, String condition, double feelslike_c, int humidity) {
        this.temp_c = temp_c;
        this.condition = condition;
        this.feelslike_c = feelslike_c;
        this.humidity = humidity;
    }
}
