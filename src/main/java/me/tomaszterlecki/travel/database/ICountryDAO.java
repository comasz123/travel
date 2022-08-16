package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.Country;

import java.util.List;

public interface ICountryDAO {
    List<Country> getAll();
    Country getCountryByNameEng(String nameEng);
    List<CitiesForAGivenCountry> getAllCitiesInCountries();
    List<Country> getAllNamesSorted ();

}
