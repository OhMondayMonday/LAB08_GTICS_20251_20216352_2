package com.example.lab08_gtics_20251_20216352_2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "monitoreo_climatico")
@Getter
@Setter
public class MonitoreoClimatico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name= "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name= "temp_promedio")
    private Double tempPromedio;

    @Column(name= "condicion_frecuente", length = 100)
    private String condicionFrecuente;

    @Column(name= "temp_maxima")
    private Double tempMaxima;

    @Column(name= "temp_minima")
    private Double tempMinima;
}
