package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.Country;

import java.util.List;

public interface ICountryDAO {
    List<Country> getAllCountries();
    Country getCountryByNameEng(String nameEng);
//    List<CitiesForAGivenCountry> getAllCitiesInAllCountries();
    List<Country> getCountryNamesSortedInEng (List<Country> countries);
    Country getCountryByID(int id);

}
