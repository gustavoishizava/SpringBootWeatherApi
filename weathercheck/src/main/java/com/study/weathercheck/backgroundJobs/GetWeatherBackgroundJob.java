package com.study.weathercheck.backgroundJobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.study.weathercheck.application.openweathermap.OpenWeatherMapService;
import com.study.weathercheck.data.CityRepository;
import com.study.weathercheck.domain.Temperature;
import com.study.weathercheck.domain.Unit;

@Component
public class GetWeatherBackgroundJob {
    private final Logger logger = LoggerFactory.getLogger(GetWeatherBackgroundJob.class);

    private final CityRepository cityRepository;
    private final OpenWeatherMapService openWeatherMapService;

    public GetWeatherBackgroundJob(CityRepository cityRepository, OpenWeatherMapService openWeatherMapService) {
        this.cityRepository = cityRepository;
        this.openWeatherMapService = openWeatherMapService;
    }

    @Scheduled(fixedDelay = 1000)
    public void GetWeather() {
        var city = cityRepository.findNextToProcess();
        if (city == null)
            return;

        do {
            logger.info(
                    String.format("Checking weather to %s,%s,%s", city.getName(), city.getState(), city.getCountry()));

            var weatherResponse = openWeatherMapService.getWeatherInfo(city.getCoordinates().latitude(),
                    city.getCoordinates().longitute());

            String weatherDescription = String.format("%s - %s", weatherResponse.weather()[0].main(),
                    weatherResponse.weather()[0].description());

            city.setWeather(weatherDescription, new Temperature(weatherResponse.main().temp(), Unit.Celsius));

            cityRepository.save(city);

            logger.info(String.format("Weather has been checked to %s,%s,%s", city.getName(), city.getState(),
                    city.getCountry()));

            city = cityRepository.findNextToProcess();
        } while (city != null);
    }
}
