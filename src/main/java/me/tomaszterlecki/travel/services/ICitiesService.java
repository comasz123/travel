package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;

import java.util.List;

public interface ICitiesService {
    List<City> getAllCitiesByCountry(Country country);
    List<CitiesForAGivenCountry> getAllCitiesInAllCountries();
    City getCityById(int cityId);
    List<City> getAllCities();
    boolean checkCityToDelete(int cityId);

}
