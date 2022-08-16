package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.database.ICountryDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.Country;
import me.tomaszterlecki.travel.services.ICitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServiceImpl implements ICitiesService {
    @Autowired
    ICityDAO cityDAO;
    @Autowired
    ICountryDAO countryDAO;

    @Override
    public List<City> getAllCitiesByCountry(Country country) {
        return this.cityDAO.getAllCitiesByCountry(country);
    }

    @Override
    public List<CitiesForAGivenCountry> getCitiesInCountry() {
        return this.countryDAO.getAllCitiesInCountries();
    }
    @Override
    public City getCityById(int cityId){
        return this.cityDAO.getCityById(cityId);
    }
}
