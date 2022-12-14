package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.database.ICountryDAO;
import me.tomaszterlecki.travel.database.IPicturesDAO;
import me.tomaszterlecki.travel.model.CitiesForAGivenCountry;
import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;
import me.tomaszterlecki.travel.services.ICitiesService;
import me.tomaszterlecki.travel.services.IPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesServiceImpl implements ICitiesService {
    @Autowired
    ICityDAO cityDAO;
    @Autowired
    ICountryDAO countryDAO;
    @Autowired
    IPicturesDAO picturesDAO;

    @Override
    public List<City> getAllCitiesByCountry(Country country) {
        return this.cityDAO.getAllCitiesByCountry(country);
    }
    @Override
    public City getCityById(int cityId){
        return this.cityDAO.getCityById(cityId);
    }

    @Override
    public List<CitiesForAGivenCountry> getAllCitiesInAllCountries(){
        List<Country> countries = countryDAO.getAllCountries();
        List<CitiesForAGivenCountry> result = new ArrayList<>();
        for (Country country : countries) {
            result.add(new CitiesForAGivenCountry(country, cityDAO.getAllCitiesByCountry(country)));
        }
        return result;
    }
    @Override
    public List<City> getAllCities(){
        return this.cityDAO.getAllCities();
    }
    @Override
    public boolean checkCityToDelete(int cityId){
        City city = cityDAO.getCityById(cityId);
        return picturesDAO.checkCity(city);
    }

}
