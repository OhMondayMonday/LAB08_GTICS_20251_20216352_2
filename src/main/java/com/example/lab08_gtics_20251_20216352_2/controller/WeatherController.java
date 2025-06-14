package com.example.lab08_gtics_20251_20216352_2.controller;

import com.example.lab08_gtics_20251_20216352_2.entity.MonitoreoClimatico;
import com.example.lab08_gtics_20251_20216352_2.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/api/weather/actual")
    public ResponseEntity<HashMap<String, Object>> obtenerPronosticoActual(@RequestParam String city) {
        return weatherService.obtenerClimaActual(city);
    }

    @GetMapping("/api/weather/horas")
    public ResponseEntity<HashMap<String, Object>> obtenerPronosticoHoras(@RequestParam String city) {
        return weatherService.obtenerPronosticoPorHoras(city);
    }

    @PostMapping("/api/weather/monitor")
    public ResponseEntity<MonitoreoClimatico> guardarMonitoreo(@RequestBody MonitoreoClimatico monitoreo) {
        return weatherService.saveMonitoreoClimatico(monitoreo);
    }
}
