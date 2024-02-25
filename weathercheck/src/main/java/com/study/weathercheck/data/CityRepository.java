package com.study.weathercheck.data;

import java.util.List;

import com.study.weathercheck.domain.City;

public interface CityRepository {
    public void save(City city);

    public List<City> findAll();

    public City findNextToProcess();
}
