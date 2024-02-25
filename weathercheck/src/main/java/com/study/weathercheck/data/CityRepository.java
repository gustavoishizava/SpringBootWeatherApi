package com.study.weathercheck.data;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.study.weathercheck.domain.City;

@Repository
public interface CityRepository extends MongoRepository<City, UUID> {
}
