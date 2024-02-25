package com.study.weathercheck.application.cityServices;

import java.util.List;

import com.study.weathercheck.application.cityServices.dtos.CreateCityRequest;
import com.study.weathercheck.application.cityServices.dtos.CreateCityResult;
import com.study.weathercheck.domain.City;

public interface CityApplicationService {
    public CreateCityResult create(CreateCityRequest request);

    public List<City> getAll();
}
