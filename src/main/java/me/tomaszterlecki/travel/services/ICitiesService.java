package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;

import java.util.List;

public interface ICitiesService {
    List<City> getAllCitiesByCountry(Country country);
    List<CitiesForAGivenCountry> getCitiesInCountry();
    City getCityById(int cityId);
    void setCountriesAndCitiesForUser();

}
