package com.study.weathercheck.application.openweathermap.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoCoordinatesResponse(float lat, float lon) {
}