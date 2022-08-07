package me.tomaszterlecki.travel.model;

import java.util.List;

public class CitiesForAGivenCountry {
    private String country;
    private List<City> cities;

    public CitiesForAGivenCountry(String country, List<City> cities) {
        this.country = country;
        this.cities = cities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
