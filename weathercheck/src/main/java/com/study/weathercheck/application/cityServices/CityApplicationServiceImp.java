package com.study.weathercheck.application.cityServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.weathercheck.application.cityServices.dtos.CreateCityRequest;
import com.study.weathercheck.application.cityServices.dtos.CreateCityResult;
import com.study.weathercheck.application.openweathermap.OpenWeatherMapService;
import com.study.weathercheck.data.CityRepository;
import com.study.weathercheck.domain.City;

@Service
public class CityApplicationServiceImp implements CityApplicationService {

    private final CityRepository repository;
    private final OpenWeatherMapService openWeatherMapService;

    public CityApplicationServiceImp(CityRepository cityRepository, OpenWeatherMapService openWeatherMapService) {
        this.repository = cityRepository;
        this.openWeatherMapService = openWeatherMapService;
    }

    @Override
    public CreateCityResult create(CreateCityRequest request) {
        var coordinates = openWeatherMapService.getCoordinates(request.name(), request.state(), request.country());
        if (coordinates == null)
            return new CreateCityResult(null, "Coordinates not found.");

        var city = new City(request.name(), request.state(), request.country(), coordinates);
        repository.save(city);

        return new CreateCityResult(city, null);
    }

    @Override
    public List<City> getAll() {
        return repository.findAll();
    }
}
