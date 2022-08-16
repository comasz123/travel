package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.ICountryDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.services.ICountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesServiceImpl implements ICountriesService {
    @Autowired
    ICountryDAO countryDAO;

    public List<Country> getAll(){
        return this.countryDAO.getAll();
    }

    @Override
    public Country getCountryByNameEng(String countryName) {
        return countryDAO.getCountryByNameEng(countryName);
    }
    @Override
    public List<Country> allCountryNamesSorted(){
        return countryDAO.getAllNamesSorted();
    }

}
