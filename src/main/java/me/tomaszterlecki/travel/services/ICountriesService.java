package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.Country;

import java.util.List;

public interface ICountriesService {
    List<Country> getAll();
    Country getCountryByNameEng(String countryName);
    List<Country> allCountryNamesSorted();
}
