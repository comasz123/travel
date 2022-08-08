package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.City;
import me.tomaszterlecki.travel.model.MonthsForAGivenYear;
import me.tomaszterlecki.travel.model.Picture;

import java.util.List;

public interface IPicturesService {


    public List<MonthsForAGivenYear> yearsAndMonths ();
    public List<City> getAllCitiesForADate (int year, String month);
    List<Picture> getPitcturesByDateAndCity(int year, String month, City city);
    List<MonthsForAGivenYear> getDatesForACity(City city);

}
