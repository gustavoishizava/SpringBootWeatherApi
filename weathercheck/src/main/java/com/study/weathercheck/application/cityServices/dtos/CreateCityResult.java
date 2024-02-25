package com.study.weathercheck.application.cityServices.dtos;

import com.study.weathercheck.domain.City;

public record CreateCityResult(City city, String error) {
    public boolean hasError() {
        return error != null;
    }
}
