package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.*;
import me.tomaszterlecki.travel.model.database.City;
import me.tomaszterlecki.travel.model.database.Country;
import me.tomaszterlecki.travel.model.database.Picture;

import java.util.List;

public interface IPicturesService {


    List<City> getAllCitiesForADate(int year, String month);

    List<Picture> getPicturesByDateAndCity(int year, String month, City city);

    List<MonthsForAGivenYear> getDatesForACity(City city);

    List<Country> getCountriesTraveled();


}
