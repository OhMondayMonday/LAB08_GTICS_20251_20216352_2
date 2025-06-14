package com.example.lab08_gtics_20251_20216352_2.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;
import java.util.HashMap;

@Service
public class WeatherService {
    private static final String API_KEY = "88e12060abad41ab97212738250906";
    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

public ResponseEntity<HashMap<String, Object>> getCurrentWeather(String city) {
    HashMap<String, Object> responseJson = new HashMap<>();
    try {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("key", API_KEY)
                .queryParam("q", city)
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class); // hacemos la llamada a la API
        
        // Verificamos que la respuesta no sea nula y que contenga los datos esperados

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
        responseJson.put("msg", "No se pudo obtener el clima: respuesta inválida"); // si la respuesta no es válida, devolvemos un mensaje de error
        return ResponseEntity.badRequest().body(responseJson);
    } catch (Exception e) {
        responseJson.put("result", "failure");
        responseJson.put("msg", "No se pudo obtener el clima: " + e.getMessage()); // capturamos cualquier excepción que ocurra
        return ResponseEntity.badRequest().body(responseJson);
    }
}
}
