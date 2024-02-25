package com.study.weathercheck.application.openweathermap;

import com.study.weathercheck.application.openweathermap.dtos.weather.WeatherResponse;
import com.study.weathercheck.domain.Coordinates;

public interface OpenWeatherMapService {
    Coordinates getCoordinates(String name, String state, String country);

    WeatherResponse getWeatherInfo(float lat, float lon);
}
