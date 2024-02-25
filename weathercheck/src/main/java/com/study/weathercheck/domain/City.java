package com.study.weathercheck.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cities")
public class City {
    @Id
    private UUID id;
    private String name;
    private String state;
    private String country;
    private Coordinates coordinates;
    private String weather;
    private Temperature temperature;
    private Status status;
    private LocalDateTime lastCheck;

    protected City() {
    }

    public City(String name, String state, String country, Coordinates coordinates) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.state = state;
        this.country = country;
        this.coordinates = coordinates;
        this.weather = null;
        this.temperature = null;
        this.status = Status.Pending;
        this.lastCheck = null;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public String getWeather() {
        return weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public String getCountry() {
        return country;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getLastCheck() {
        return lastCheck;
    }

    public void setWeather(String weather, Temperature temperature) {
        this.weather = weather;
        this.temperature = temperature;
        this.status = Status.Checked;
        this.lastCheck = LocalDateTime.now();
    }
}
