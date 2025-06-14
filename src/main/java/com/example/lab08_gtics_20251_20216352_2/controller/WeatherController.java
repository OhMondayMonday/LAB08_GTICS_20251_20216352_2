package com.example.lab08_gtics_20251_20216352_2.controller;

import com.example.lab08_gtics_20251_20216352_2.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/api/weather/current")
    public ResponseEntity<HashMap<String, Object>> getCurrentWeather(@RequestParam String city) {
        return weatherService.getCurrentWeather(city);
    }
}
