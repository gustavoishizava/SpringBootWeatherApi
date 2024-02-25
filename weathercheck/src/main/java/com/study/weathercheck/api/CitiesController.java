package com.study.weathercheck.api;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.study.weathercheck.application.cityServices.CityApplicationService;
import com.study.weathercheck.application.cityServices.dtos.CreateCityRequest;
import com.study.weathercheck.domain.City;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("cities")
public class CitiesController {
    private final CityApplicationService cityApplicationService;

    public CitiesController(CityApplicationService cityApplicationService) {
        this.cityApplicationService = cityApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {
        return new ResponseEntity<List<City>>(cityApplicationService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody CreateCityRequest request) {
        var result = cityApplicationService.create(request);
        if (result.hasError())
            return new ResponseEntity<String>(result.error(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<City>(result.city(), HttpStatus.OK);
    }
}
