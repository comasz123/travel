package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;

import java.util.List;

public interface ICityDAO {
    List<City> getAllCitiesByCountry(Country country);
    City getCityById(int id);
}
