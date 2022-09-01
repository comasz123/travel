package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.IPicturesDAO;
import me.tomaszterlecki.travel.model.*;
import me.tomaszterlecki.travel.services.IPicturesService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PicturesServiceImpl implements IPicturesService {
    @Autowired
    IPicturesDAO picturesDAO;
    @Autowired
    SessionObject sessionObject;

    public List<City> getAllCitiesForADate (int year, String month){
        return picturesDAO.getAllCitiesForADate(year, month);
    }
    public List<Picture> getPicturesByDateAndCity(int year, String month, City city){
        return picturesDAO.getPicturesByUserDateAndCity(year, month, city);
    }
    public List<MonthsForAGivenYear> getDatesForACity(City city){
       return picturesDAO.getDatesForACity(city);
    }
    public List<Country> getCountriesTraveled(){
        List<Country> countries = new ArrayList<>();
        List<CitiesForAGivenCountry> citiesForAGivenCountries = sessionObject.getCitiesForAGivenCountries();
        for (CitiesForAGivenCountry element :
                citiesForAGivenCountries) {
            countries.add(element.getCountry());
        }
        return countries;
    }


}
