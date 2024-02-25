package com.study.weathercheck.application.openweathermap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.study.weathercheck.application.openweathermap.dtos.GeoCoordinatesResponse;
import com.study.weathercheck.domain.Coordinates;

@Service
public class OpenWeatherMapServiceImp implements OpenWeatherMapService {

    @Value("${openweathermap.apikey}")
    private String apiKey;

    @Value("${openweathermap.baseurl}")
    private String baseUrl;

    private final Logger logger = LoggerFactory.getLogger(OpenWeatherMapServiceImp.class);

    private final RestTemplate restTemplate;

    public OpenWeatherMapServiceImp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Coordinates getCoordinates(String name, String state, String country) {
        logger.info(String.format("Trying to get coordinates to %s,%s,%s", name, state, country));

        String path = "/geo/1.0/direct";
        String url = baseUrl + path + String.format("?q=%s,%s,%s&appid=%s", name, state, country, apiKey);

        GeoCoordinatesResponse[] response = restTemplate.getForObject(url, GeoCoordinatesResponse[].class);

        if (response.length == 0)
            return null;

        return new Coordinates(response[0].lat(), response[0].lon());
    }
}
