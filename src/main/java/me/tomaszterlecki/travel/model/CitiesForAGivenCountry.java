package me.tomaszterlecki.travel.model;

import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;

import java.util.List;

public class CitiesForAGivenCountry {
    private Country country;
    private List<City> cities;

    public CitiesForAGivenCountry(Country country, List<City> cities) {
        this.country = country;
        this.cities = cities;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
