package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.database.Country;

import java.util.List;

public interface ICountriesService {
    List<Country> getAllCountries();
    List<Country> getAllCountriesTravelled();
    Country getCountryByNameEng(String countryName);
    Country getCountryByID(int id);

}
