package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;

import java.util.List;

public interface ICityDAO {
    List<City> getAllCitiesByCountry(Country country);

    void setCountriesAndCitiesForUser();
    City getCityById(int id);
    List<City> getAllCities();

}
