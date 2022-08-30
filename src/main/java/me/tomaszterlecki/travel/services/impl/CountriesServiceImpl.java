package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.ICountryDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.services.ICountriesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesServiceImpl implements ICountriesService {
    @Autowired
    ICountryDAO countryDAO;
    SessionObject sessionObject;

    public List<Country> getAllCountries(){
        return this.countryDAO.getAllCountries();
    }
    public List<Country> getAllCountriesTravelled(){
        List<Country> countries = new ArrayList<>();
        for (CitiesForAGivenCountry element : sessionObject.getCitiesForAGivenCountries()) {
            countries.add(element.getCountry());
        }
        return countries;
    }

    @Override
    public Country getCountryByNameEng(String countryName) {
        return countryDAO.getCountryByNameEng(countryName);
    }
    @Override
    public Country getCountryByID(int id){
        return countryDAO.getCountryByID(id);
    }

}
