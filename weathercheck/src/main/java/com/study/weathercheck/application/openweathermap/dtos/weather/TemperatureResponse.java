package com.study.weathercheck.application.openweathermap.dtos.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TemperatureResponse(double temp) {
}
