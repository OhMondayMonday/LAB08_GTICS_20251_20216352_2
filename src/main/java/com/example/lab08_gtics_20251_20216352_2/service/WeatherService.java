package com.example.lab08_gtics_20251_20216352_2.service;

import com.example.lab08_gtics_20251_20216352_2.entity.MonitoreoClimatico;
import com.example.lab08_gtics_20251_20216352_2.repository.MonitoreoClimaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private static final String API_KEY = "88e12060abad41ab97212738250906";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    @Autowired
    private MonitoreoClimaticoRepository monitoreoClimaticoRepository;

    public ResponseEntity<HashMap<String, Object>> obtenerClimaActual(String city) {
        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = UriComponentsBuilder.fromUriString(BASE_URL)
                    .queryParam("key", API_KEY)
                    .queryParam("q", city)
                    .toUriString();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.get("current") instanceof Map) {
                Map<String, Object> current = (Map<String, Object>) response.get("current");
                if (current.get("condition") instanceof Map) {
                    Map<String, Object> conditionMap = (Map<String, Object>) current.get("condition");
                    responseJson.put("result", "success");
                    responseJson.put("temp_c", current.get("temp_c"));
                    responseJson.put("condition", conditionMap.get("text"));
                    responseJson.put("feelslike_c", current.get("feelslike_c"));
                    responseJson.put("humidity", current.get("humidity"));
                    return ResponseEntity.ok(responseJson);
                }
            }
            responseJson.put("result", "failure");
            responseJson.put("msg", "No se pudo obtener el clima: respuesta inválida");
            return ResponseEntity.badRequest().body(responseJson);
        } catch (Exception e) {
            responseJson.put("result", "failure");
            responseJson.put("msg", "No se pudo obtener el clima: " + e.getMessage());
            return ResponseEntity.badRequest().body(responseJson);
        }
    }

    public ResponseEntity<HashMap<String, Object>> obtenerPronosticoPorHoras(String city) {
        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = UriComponentsBuilder.fromUriString("https://api.weatherapi.com/v1/forecast.json")
                    .queryParam("key", API_KEY)
                    .queryParam("q", city)
                    .queryParam("days", 1)
                    .toUriString();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            responseJson.put("city", city);
            Map<String, Object> forecast = (Map<String, Object>) response.get("forecast");
            if (forecast == null) throw new Exception("No se encontró forecast");
            java.util.List<Map<String, Object>> forecastday = (java.util.List<Map<String, Object>>) forecast.get("forecastday");
            if (forecastday == null || forecastday.isEmpty()) throw new Exception("No se encontró forecastday");
            Map<String, Object> today = forecastday.get(0);
            java.util.List<Map<String, Object>> hours = (java.util.List<Map<String, Object>>) today.get("hour");
            java.util.List<HashMap<String, Object>> forecastList = new java.util.ArrayList<>();
            for (Map<String, Object> hour : hours) {
                HashMap<String, Object> hourMap = new HashMap<>();
                String hourTime = (String) hour.get("time");
                String hourOnly = hourTime.substring(hourTime.length() - 5);
                hourMap.put("hour", hourOnly);
                hourMap.put("temp_c", hour.get("temp_c"));
                Map<String, Object> condition = (Map<String, Object>) hour.get("condition");
                hourMap.put("condition", condition.get("text"));
                forecastList.add(hourMap);
            }
            responseJson.put("forecast", forecastList);
            return ResponseEntity.ok(responseJson);
        } catch (Exception e) {
            responseJson.put("result", "failure");
            responseJson.put("msg", "No se pudo obtener el pronóstico: " + e.getMessage());
            return ResponseEntity.badRequest().body(responseJson);
        }
    }

    public ResponseEntity<MonitoreoClimatico> saveMonitoreoClimatico(MonitoreoClimatico monitoreo) {
        MonitoreoClimatico saved = monitoreoClimaticoRepository.save(monitoreo);
        return ResponseEntity.ok(saved);
    }
}
